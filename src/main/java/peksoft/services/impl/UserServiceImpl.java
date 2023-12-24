package peksoft.services.impl;

import peksoft.dto.SimpleResponse;
import peksoft.dto.request.AuthRequest;
import peksoft.dto.request.UserRequest;
import peksoft.dto.response.AuthResponse;
import peksoft.entities.Restaurant;
import peksoft.entities.User;
import peksoft.enums.Role;
import peksoft.exceptions.BadCredentialsException;
import peksoft.exceptions.NotFoundException;
import peksoft.repo.RestaurantRepo;
import peksoft.repo.UserRepo;
import peksoft.security.JwtService;
import peksoft.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final RestaurantRepo restaurantRepo;

    @PostConstruct
    @Override
    public void init() {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setFirstName("nuriza");
        user.setLastName("Zununova");
        user.setDateOfBirth(LocalDate.of(2006, 2, 8));
        user.setEmail("zununovanuriza0@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setPhoneNumber("+996709220262");
        user.setExperience(5);

        if (!userRepo.existsByEmail("zununovanuriza0@gmail.com")) {
            try {
                userRepo.save(user);
            } catch (DataAccessException e) {
                log.error("Error while saving user during initialization: " + e.getMessage(), e);
                // Handle error
            }
        }
    }

    @Override
    public SimpleResponse signUp(UserRequest signUp, Long restId) {
        if (StringUtils.isBlank(signUp.password())) {
            throw new IllegalArgumentException("Password cannot be blank");
        }

        if (StringUtils.isBlank(signUp.email())) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        if (!userRepo.existsByEmail(signUp.email())) {
            Restaurant restaurant = restaurantRepo.findById(restId).orElseThrow(() ->
                    new NotFoundException("Restaurant with id: %s not found".formatted(restId)));

            User user = new User();
            user.setFirstName(signUp.firstName());
            user.setLastName(signUp.lastName());
            user.setDateOfBirth(signUp.dateOfBirth());
            user.setEmail(signUp.email());
            user.setPassword(passwordEncoder.encode(signUp.password()));
            user.setPhoneNumber(signUp.phoneNumber());
            user.setRole(signUp.role());
            user.setExperience(signUp.experience());
            user.setRestaurant(restaurant);

            try {
                userRepo.save(user);
                log.info("User successfully saved");
                return SimpleResponse.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("Successfully save user with id: " + user.getId())
                        .build();
            } catch (DataAccessException e) {
                log.error("Error while saving user: " + e.getMessage(), e);
                // Handle error
            }
        } else {
            throw new IllegalArgumentException("User with email " + signUp.email() + " already exists");
        }

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Failed to save user")
                .build();
    }

    @Override
    public AuthResponse signIn(AuthRequest signInRequest) {
        if (StringUtils.isBlank(signInRequest.email())) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        User user = userRepo.getUserByEmail(signInRequest.email()).orElseThrow(() ->
                new UsernameNotFoundException("User with email: " + signInRequest.email() + " not found"));

        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }

        Role role = user.getRole();
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .token(jwtToken)
                .role(role)
                .build();
    }
}

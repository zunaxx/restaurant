package peksoft.entities;

import peksoft.enums.Role;
import peksoft.validations.PasswordValidation;
import peksoft.validations.PhoneNumberValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Email
    @Column(unique = true)
    private String email;

    @PasswordValidation
    private String password;

    @PhoneNumberValidation(message = "Phone number must start with country code '+996' and must be 13 characters long")
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private Role role;

    private int experience;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "user")
    private List<Cheque>cheques;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

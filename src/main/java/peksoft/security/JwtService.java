package peksoft.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@Component
public class JwtService {

    @Value("${spring.jwt.secret_key}")
    private String SECRET_KEY;
    public String generateToken(UserDetails userDetails){
        return JWT.create()
                .withClaim("username",userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(60).toInstant()))
                .sign(Algorithm.HMAC256(SECRET_KEY));

    }

    public String validateToken(String token){  // mojet byt pod kopotom admin@gmail.com
        JWTVerifier jwtVerifier=
                JWT.require(
                        Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}











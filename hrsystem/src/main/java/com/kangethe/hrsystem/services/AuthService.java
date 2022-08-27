package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.payload.requests.SignInRequest;
import com.kangethe.hrsystem.payload.requests.SignUpRequest;
import com.kangethe.hrsystem.security.jwt.JwtUtils;
import com.kangethe.hrsystem.security.services.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserService userService;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    /**
     * Generates a JWT token for the validated client
     */
    public String generateToken(UserDetailsImpl user) {
        return jwtUtils.generateJwtToken(user);
    }

    /**
     * Registers a new user in the database by performing a series of quick checks.
     *
     * @return A user object if successfully created
     */
    public ResponseEntity<User>  registerUser(SignUpRequest signupRequest){
        if (!userService.checkEmailAvailable(signupRequest.getEmail())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(userService.save(userService.createUser(signupRequest)));
    }

    /**
     * Authenticate user and log them in given a loginRequest
     */
    public Authentication authenticateUser(SignInRequest signInRequest) {
        Optional<User> userOptional = userService.getUserByEmail(signInRequest.getEmail());

        return userOptional.map(user -> authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        signInRequest.getPassword()
                )
        )).orElse(null);
    }

//
//    fun getCurrentlyLoggedInService(): UserDetailsImpl? {
//        return SecurityContextHolder.getContext().authentication?.principal as UserDetailsImpl ?: null
//    }
}

package com.kangethe.hrsystem.RestControllers;


import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.exception.UnauthenticatedException;
import com.kangethe.hrsystem.payload.requests.EmailAvailableRequest;
import com.kangethe.hrsystem.payload.requests.SignInRequest;
import com.kangethe.hrsystem.payload.requests.SignUpRequest;
import com.kangethe.hrsystem.payload.responses.AvailabilityResponse;
import com.kangethe.hrsystem.payload.responses.JwtAuthenticationResponse;
import com.kangethe.hrsystem.security.services.UserDetailsImpl;
import com.kangethe.hrsystem.services.AuthService;
import com.kangethe.hrsystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "This authenticates users into the system.")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);


    private final UserService userService;
    private final AuthService authService;


    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }


    @Operation(summary = "Check if email is available", description = "checks Email availability", tags = {"Authentication"})
    @PostMapping("/check/email")
    public ResponseEntity<AvailabilityResponse> emailAvailable(@Valid @RequestBody EmailAvailableRequest request) {
        return ResponseEntity.ok(new AvailabilityResponse(userService.checkEmailAvailable(request.getEmail())));
    }

    @Operation(summary = "sign in the user, retrieve the user token", description = "Signs in the user, gets the user token", tags = {"Authentication"})
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication = authService.authenticateUser(signInRequest);
        if(authentication == null){
            throw new UnauthenticatedException("Try Logging in first");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl customUserDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = authService.generateToken(customUserDetails);
        Optional<User> userOptional = userService.getUserByEmail(signInRequest.getEmail());
        User user = null;
        if (userOptional.isPresent()){
            user = userOptional.get();
        } else {
            throw new NotFoundException("User Not Found");
        }

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, "bearer", user));
    }

    @Operation(summary = "sign up a new user", description = "Signs Up a new User", tags = {"Authentication"})
    @PostMapping("/signupForm")
    public ResponseEntity<User> signUpRequestBody(@RequestParam("email") String email, @RequestParam("isAdmin") Boolean is_Admin, @RequestParam("isModerator") Boolean is_Moderator, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirm_password) {
        SignUpRequest signUpRequest = new SignUpRequest( email, password, is_Admin, is_Moderator);
        return authService.registerUser(signUpRequest);
    }
    @Operation(summary = "sign up a new user", description = "Signs Up a new User", tags = {"Authentication"})
    @PostMapping("/signup")
    public ResponseEntity<User> signUpRequestBody(@RequestBody  SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}

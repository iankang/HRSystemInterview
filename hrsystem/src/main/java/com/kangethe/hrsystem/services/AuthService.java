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

    //    private var emailVerificationService: EmailVerificationService,
//    private var passwordResetService: PasswordResetService
//
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

        if (!userService.checkUsernameAvailable(signupRequest.getUsername())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(userService.save(userService.createUser(signupRequest)));
    }

    /**
     * Authenticate user and log them in given a loginRequest
     */
    public Authentication authenticateUser(SignInRequest signInRequest) {
        User user = userService.getUserByEmail(signInRequest.getEmail());

        if (user != null) {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            signInRequest.getPassword()
                    )
            );
        }
        return  null;
    }
//
//
//    /**
//     * Verify email token from user
//     *
//     * @return
//     */
//    fun verifyEmailToken(token: String) {
//        if (!emailVerificationService.tokenExists(token)) {
//            throw NotFoundException(
//                    "Provided token could not be found. Please request for a new verification email"
//            )
//        }
//
//
//        val emailEntity = emailVerificationService.getVerificationEmailByToken(token)!!
//                emailVerificationService.verifyExpiration(emailEntity)
//
//        val user = userService.getUserById(emailEntity.userId!!)
//            ?: throw NotFoundException(
//                "User not found, please contact support or request for a new verification email."
//        )
//
//        user.isEmailVerified = true
//        user.isActive = true
//
//        userService.save(user)
//        emailVerificationService.remove(emailEntity)
//    }
//
//    /**
//     * Verify email token from user
//     *
//     * @return
//     */
//    fun recreateEmailVerificationToken(user: User): UserEmailVerification {
//        var token = emailVerificationService.getVerificationEmailByUserId(user.id!!)
//        if (token == null) {
//            token = emailVerificationService.createVerificationToken(
//                    user,
//                    emailVerificationService.generateToken()
//            )
//        }
//
//        return emailVerificationService.updateExistingTokenExpiry(token)
//    }
//
//    /**
//     * Generates a password reset token from the given reset request
//     */
//    fun generatePasswordResetToken(userId: Long): UserPasswordReset? {
//        if (passwordResetService.tokenExistsByUserId(userId)) {
//            passwordResetService.remove(
//                    passwordResetService.findByUserId(userId)!!
//            )
//        }
//
//        return passwordResetService.save(
//                passwordResetService.createResetToken(
//                        userId
//                )
//        )
//    }
//
//    /**
//     * Reset a password given a reset request and return the updated user
//     */
//    fun resetPassword(resetPasswordRequest: ResetPasswordRequest): User? {
//        if (resetPasswordRequest.password.trim() != resetPasswordRequest.confirm_password.trim()) {
//            throw BadRequestException("Passwords do not match!")
//        }
//
//        val passwordResetToken = passwordResetService.findByToken(resetPasswordRequest.token)
//                ?: throw NotFoundException(
//                "Provided token could not be found. Please request for a new password reset"
//        )
//
//        passwordResetService.verifyExpiration(passwordResetToken)
//        val user = userService.getUserById(passwordResetToken.userId!!)
//            ?: throw NotFoundException(
//                "User not found, please contact support or request for a new password reset."
//        )
//
//        user.password = passwordEncoder.encode(resetPasswordRequest.password)
//
//        passwordResetService.remove(passwordResetToken)
//        return userService.save(user)
//    }
//
//    fun getCurrentlyLoggedInService(): UserDetailsImpl? {
//        return SecurityContextHolder.getContext().authentication?.principal as UserDetailsImpl ?: null
//    }
}

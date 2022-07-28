package com.kangethe.hrsystem.RestControllers;


import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.payload.requests.EmailAvailableRequest;
import com.kangethe.hrsystem.payload.requests.SignInRequest;
import com.kangethe.hrsystem.payload.requests.SignUpRequest;
import com.kangethe.hrsystem.payload.responses.AvailabilityResponse;
import com.kangethe.hrsystem.payload.responses.JwtAuthenticationResponse;
import com.kangethe.hrsystem.security.services.UserDetailsImpl;
import com.kangethe.hrsystem.services.AuthService;
import com.kangethe.hrsystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


//
//    @Operation(summary = "Verify email by token")
//    @GetMapping("/email/verification")
//    fun verifyEmail(
//            @Param("token")token: String?
//    ): ResponseEntity<MessageResponse> {
//        if (token.isNullOrEmpty()) {
//            throw BadRequestException("Invalid verification token. Please request for a new email verification link")
//        }
//
//        authService.verifyEmailToken(token)
//
//        return ResponseEntity.ok(
//                MessageResponse("Email verified successfully ")
//        )
//    }
//
//    @Operation(summary = "Resend verification email incase kimeumana")
//    @PostMapping("/email/verification/resend")
//    fun resendVerificationEmail(
//            @Valid @RequestBody emailRequest: ResendEmailRequest
//    ): ResponseEntity<MessageResponse> {
//        val user = userService.getUserByEmail(emailRequest.email)
//                ?: throw NotFoundException(
//                "User with provided email not found, please contact support or request for a new verification email."
//        )
//
//        if (user.isEmailVerified!!) {
//            throw ForbiddenRequestException(
//                    "User email already verified. Please proceed to sign in!"
//            )
//        }
//
//        val emailToken = authService.recreateEmailVerificationToken(user)
//        val regenerateEmailVerificationEvent = OnRegenerateEmailVerificationEvent(user, emailToken)
//        applicationEventPublisher.publishEvent(regenerateEmailVerificationEvent)
//        return ResponseEntity.ok(
//                MessageResponse("Email verification resent successfully. Please check your email for verification!")
//        )
//    }
//
//    @Operation(summary = "Receive the reset link request and send mail containing the password reset link")
//    @PostMapping("/password/reset")
//    fun resetPasswordLink(
//            @Valid @RequestBody request: ResetPasswordLinkRequest,
//            ): ResponseEntity<MessageResponse> {
//        val user = userService.getUserByEmail(request.email)
//                ?: throw NotFoundException(
//                "User not found, please contact support or request for a new password reset."
//        )
//
//        val passwordRequest = authService.generatePasswordResetToken(user.id)
//                ?: throw InternalServerException(
//                "Error generating password reset link. Please try again or contact support."
//        )
//
//        val generateResetLinkMailEvent = OnGenerateResetLinkEvent(user, passwordRequest)
//        applicationEventPublisher.publishEvent(generateResetLinkMailEvent)
//
//        return ResponseEntity.ok(
//                MessageResponse("Password reset link sent successfully. Please check your email for verification!")
//        )
//    }
//
//    @Operation(summary = "Reset the password after verification and publish an event to send the acknowledgement email")
//    @PostMapping("/password/reset/verification")
//    fun resetPasswordVerification(
//            @Valid @RequestBody request: ResetPasswordRequest,
//            ): ResponseEntity<MessageResponse> {
//        val user = authService.resetPassword(request)
//                ?: throw InternalServerException(
//                "An error occurred resetting you password, please contact support or request for a new password reset."
//        )
//        applicationEventPublisher.publishEvent(
//                OnUserAccountChangeEvent(
//                        user, "Reset Password", "Changed Successfully"
//                )
//        )
//        return ResponseEntity.ok(
//                MessageResponse("Password changed successfully!")
//        )
//    }
//    @GetMapping("/currentUser")
//    fun getCurrentLoggedInUser(): UserDetailsImpl? {
//        return authService.getCurrentlyLoggedInService()
//    }
//
//    @GetMapping(value = ["/getPic/{name}"], produces = [MediaType.IMAGE_JPEG_VALUE])
//    @Throws(
//            MinioException::class
//    )
//    fun getImage(@PathVariable("name") name: String): Resource? {
//        val filePath: Path = Paths.get(lunnaDevResourceDir+ File.separator+ ResourceType.USER_PROF.name)
//                .toAbsolutePath().normalize().resolve(name).normalize()
//        //   Resource resource = new UrlResource(filePath.toUri());
//        val inputStream: InputStream = minioService.get(filePath)
//        val inputStreamResource = InputStreamResource(inputStream)
//        return if (inputStreamResource.exists()) {
//            inputStreamResource
//        } else {
//            throw FileNotFoundException("File not found $name")
//        }
//    }

//    @Autowired
//    AuthenticationManager authenticationManager;


    private final UserService userService;
    private final AuthService authService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    JwtUtils jwtUtils;


    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }


    @Operation(summary = "Check if email is available")
    @PostMapping("/check/email")
    public ResponseEntity<AvailabilityResponse> emailAvailable(@Valid @RequestBody EmailAvailableRequest request) {
        return ResponseEntity.ok(new AvailabilityResponse(userService.checkEmailAvailable(request.getEmail())));
    }

    @Operation(summary = "sign in the user, retrieve the user token")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication = authService.authenticateUser(signInRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl customUserDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = authService.generateToken(customUserDetails);
//        val refreshToken = authService.createAndPersistRefreshToken(customUserDetails.username.orEmpty())
        User user = userService.getUserByEmail(signInRequest.getEmail());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, "bearer", user));
    }

    @Operation(summary = "sign up a new user")
    @PostMapping("/signupForm")
    public ResponseEntity<User> signUpRequestBody(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("isAdmin") Boolean is_Admin, @RequestParam("isModerator") Boolean is_Moderator, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirm_password) {
        SignUpRequest signUpRequest = new SignUpRequest(username, email, password, is_Admin, is_Moderator);

        return authService.registerUser(signUpRequest);

    }
}

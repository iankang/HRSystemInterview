package com.kangethe.hrsystem.RestControllers;


import com.kangethe.hrsystem.entities.ERole;
import com.kangethe.hrsystem.entities.Role;
import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.payload.requests.LoginRequest;
import com.kangethe.hrsystem.payload.requests.SignUpRequest;
import com.kangethe.hrsystem.payload.responses.JwtResponse;
import com.kangethe.hrsystem.payload.responses.MessageResponse;
import com.kangethe.hrsystem.repositories.RoleRepository;
import com.kangethe.hrsystem.repositories.UserRepository;
import com.kangethe.hrsystem.security.jwt.JwtUtils;
import com.kangethe.hrsystem.security.services.UserDetailsImpl;
import com.kangethe.hrsystem.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

//
//    @Operation(summary = "Check if username is available")
//    @PostMapping("/check/username")
//    fun usernameAvailable(
//            @Valid @RequestBody request: UsernameAvailableRequest
//    ): ResponseEntity<AvailabilityResponse> {
//        return ResponseEntity.ok(AvailabilityResponse(userService.checkUsernameAvailable(request.username)))
//    }
//
//    @Operation(summary = "Check if email is available")
//    @PostMapping("/check/email")
//    fun emailAvailable(
//            @Valid @RequestBody request: EmailAvailableRequest
//    ): ResponseEntity<AvailabilityResponse> {
//        return ResponseEntity.ok(AvailabilityResponse(userService.checkEmailAvailable(request.email)))
//    }
//
//
//    @Operation(summary = "sign up a new user")
//    @PostMapping("/signup")
//    fun signUp(
//            @Valid @RequestBody signUpRequest: SignUpRequest
//    ): ResponseEntity<MessageResponse> {
//        val user = authService.registerUser(signUpRequest) ?: throw ExpectationFailedException(
//                "Failed to register user ${signUpRequest.username}"
//        )
//
//
//        val onUserRegistrationCompleteEvent = OnUserRegistrationCompleteEvent(user)
//        applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent)
//        return ResponseEntity.ok(
//                MessageResponse("Sign up successful! Please Check your email for verification")
//        )
//    }
//    @Operation(summary = "sign up a new user")
//    @PostMapping(
//            value = ["/signupForm"],
//            consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
//    )
//    fun signUpRequestBody(
//            @RequestParam("username") username: String,
//            @RequestParam("email") email: String,
//            @RequestParam("firstName") first_name: String,
//            @RequestParam("lastName") last_name: String,
//            @RequestParam("isAdmin") is_Admin: Boolean = false,
//            @RequestParam("isModerator") is_Moderator: Boolean = false,
//            @RequestParam("password") password: String,
//            @RequestParam("confirmPassword") confirm_password: String,
//            @RequestPart("file") hackathonPoster:MultipartFile
//    ): ResponseEntity<MessageResponse> {
//        val signUpRequest = SignUpRequest(
//                username, email, first_name, last_name, is_Admin, is_Moderator, password, confirm_password
//        )
//        val user = authService.registerUser(signUpRequest) ?: throw ExpectationFailedException(
//                "Failed to register user ${signUpRequest.username}"
//        )
//
//        val userProfileUrl= fileManagerService.uploadImage(ResourceType.USER_PROF,hackathonPoster, user.username)
//        user.devProfPicUrl = userProfileUrl?.fileName.toString()
//
//        userService.save(user)
//        val onUserRegistrationCompleteEvent = OnUserRegistrationCompleteEvent(user)
//        applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent)
//        return ResponseEntity.ok(
//                MessageResponse("Sign up successful! Please Check your email for verification")
//        )
//    }
//
//    @Operation(summary = "sign in the user, retrieve the user token")
//    @PostMapping("/signin")
//    fun signIn(
//            @Valid @RequestBody signInRequest: SignInRequest
//    ): ResponseEntity<JWTAuthenticationResponse> {
//        val authentication = authService.authenticateUser(signInRequest)
//        SecurityContextHolder.getContext().authentication = authentication
//
//        val customUserDetails = authentication?.principal as UserDetailsImpl
//        val jwt = authService.generateToken(customUserDetails)
////        val refreshToken = authService.createAndPersistRefreshToken(customUserDetails.username.orEmpty())
//        val user = userService.getUserByEmail(signInRequest.identifier)
//        val onUserSignedInEvent = OnUserLoginCompleteEvent(user!!)
//        applicationEventPublisher.publishEvent(onUserSignedInEvent)
//        return ResponseEntity.ok(
//                JWTAuthenticationResponse(
//                        access_token = jwt.orEmpty(),
//                        user = user!!
//            )
//        )
//    }
//
//
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

//    @Autowired
//    UserService userService;
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

    @GetMapping("/signin")
    public ResponseEntity<?> hello(){
        return  ResponseEntity.ok("oya");
    }
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = userService.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
//        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
//        if (userRepository.existsByUsername(request.getUsername())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: username already taken"));
//        }
//        if (userRepository.existsByEmail(request.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: email already taken"));
//        }
//
//        User user = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
//
//        Set<String> strRoles = request.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
//                        roles.add(adminRole);
//                        break;
//                    case "mod":
//                        Role mod = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error. Role is not found"));
//                        roles.add(mod);
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error. Role is not found"));
//                        roles.add(userRole);
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered Successfully!"));
//    }
}

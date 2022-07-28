package com.kangethe.hrsystem.security.services;

import com.kangethe.hrsystem.entities.ERole;
import com.kangethe.hrsystem.entities.Role;
import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.payload.requests.SignUpRequest;
import com.kangethe.hrsystem.repositories.RoleRepository;
import com.kangethe.hrsystem.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public ResponseEntity<User> getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        if (userOptional.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Boolean checkEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public User createUser(SignUpRequest signUpRequest) {
        return new User(signUpRequest.getUsername().trim(), signUpRequest.getEmail().trim(), passwordEncoder.encode(signUpRequest.getPassword().trim()), setRolesForNewUser(signUpRequest.getModerator(), signUpRequest.getAdmin()), false, false);
    }

    public Boolean checkUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public Set<Role> setRolesForNewUser(Boolean isModerator, Boolean isAdmin) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
        if (userRole.isPresent()) {
            roles.add(userRole.get());
        }

        Optional<Role> moderatorUserOptional = roleRepository.findByName(ERole.ROLE_MODERATOR);
        if (isModerator) {
            if (moderatorUserOptional.isPresent()) {
                roles.add(moderatorUserOptional.get());
            }
        }

        Optional<Role> adminUserOptional = roleRepository.findByName(ERole.ROLE_ADMIN);

        if (isAdmin) {
            if (adminUserOptional.isPresent()) {
                roles.add(adminUserOptional.get());
            }
        }

        return new HashSet<>(roles);
    }


    public User save(User user) {
        return userRepository.save(user);
    }
}

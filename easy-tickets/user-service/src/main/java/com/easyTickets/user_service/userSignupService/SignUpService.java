package com.easyTickets.user_service.userSignupService;

import com.easyTickets.user_service.userModel.UserDetails;
import com.easyTickets.user_service.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> registerUser(String username, String email, String password) {
        // Check if the user already exists by username or email
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        // Hash the password
        String encodedPassword = passwordEncoder.encode(password);

        // Save the user
        UserDetails user = new UserDetails();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER"); // Default role

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}

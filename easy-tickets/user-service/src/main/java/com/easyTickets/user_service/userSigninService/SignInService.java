package com.easyTickets.user_service.userSigninService;
import com.easyTickets.user_service.userModel.UserDetails;
import com.easyTickets.user_service.userModel.UserLogin;
import com.easyTickets.user_service.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SignInService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticateUser(UserLogin loginRequest) {
        // Find user by username
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        // Verify the password
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return "Login successful!";
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }
}

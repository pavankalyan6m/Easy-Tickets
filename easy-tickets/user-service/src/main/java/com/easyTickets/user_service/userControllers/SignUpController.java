package com.easyTickets.user_service.userControllers;

import com.easyTickets.user_service.userModel.UserDetails;
import com.easyTickets.user_service.userSignupService.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SignUpController {

    @Autowired
    private SignUpService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDetails signUpRequest) {
        ResponseEntity<String> response = signupService.registerUser(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword()
        );
        return response;
    }
}

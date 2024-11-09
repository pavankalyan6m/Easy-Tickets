package com.easyTickets.user_service.userControllers;

import com.easyTickets.user_service.userModel.UserDetails;
import com.easyTickets.user_service.userModel.UserLogin;
import com.easyTickets.user_service.userSigninService.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class SignInController {

    @Autowired
    private SignInService signinService;

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody UserLogin loginRequest) {
        try {
            String response = signinService.authenticateUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}

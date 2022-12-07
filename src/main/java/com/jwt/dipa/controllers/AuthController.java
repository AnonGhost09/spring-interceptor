package com.jwt.dipa.controllers;

import com.jwt.dipa.constansts.UrlMappings;
import com.jwt.dipa.models.requests.UserRequest;
import com.jwt.dipa.services.AuthServiceImpl;
import com.jwt.dipa.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMappings.AUTH_URL)
public class AuthController {
    @Autowired
    private final AuthServiceImpl authService;

    @Autowired
    JwtUtil jwtUtil;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity authentication(@RequestBody UserRequest userRequest){
        String token = authService.login(userRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/token-validation")
    public ResponseEntity tokenValidation(@RequestParam String token){
        if(authService.validateToken(token)){
            return ResponseEntity.ok("Token is valid");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestParam String token){
            if(authService.logout(token)){
                return ResponseEntity.ok("Logout Successfully");
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Logout failed, Token is invalid");
            }
    }
}

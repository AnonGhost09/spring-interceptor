package com.jwt.dipa.services;

import com.jwt.dipa.exception.UnauthorizedException;
import com.jwt.dipa.models.requests.UserRequest;
import com.jwt.dipa.repositories.AuthRepositoryImpl;
import com.jwt.dipa.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private AuthRepositoryImpl authRepository;

    public AuthServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String login (UserRequest userRequest){
      return authRepository.login(userRequest);
    }


    public Boolean logout(String token){
        return authRepository.logout(token);
    }

    public Boolean validateToken(String token){
        return authRepository.validateToken(token);
    }
}

package com.jwt.dipa.repositories;

import com.jwt.dipa.exception.UnauthorizedException;
import com.jwt.dipa.models.requests.UserRequest;
import com.jwt.dipa.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthRepositoryImpl {
    private final List<String> tokenStorage = new ArrayList<>();
    @Autowired
    private final JwtUtil jwtUtil;

    public AuthRepositoryImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String login (UserRequest userRequest){
        System.out.println(userRequest.getPassword() + userRequest.getEmail());
        if(userRequest.getPassword().equals("difaks") && userRequest.getEmail().equals("avatardiva@yahoo.com")){
            String token = jwtUtil.generateToken("userId:1");
            System.out.println(token);
            tokenStorage.add(token);
            return token;
        }else{
            throw new UnauthorizedException("Invalid user name or password");
        }
    }

    public Boolean validateToken(String token){
        String existingToken = null;

        for (String sToken :
                tokenStorage) {
            if(sToken.equals(token)){
                existingToken = sToken;
                break;
            }
        }

        if(existingToken==null){
            throw new UnauthorizedException("Token not exist");
        }

        if(jwtUtil.validateToken(existingToken)){
            return true;
        }else{
            throw new UnauthorizedException("Token invalid");
        }
    }

    public Boolean logout(String token){
        return tokenStorage.remove(token);
    }
}

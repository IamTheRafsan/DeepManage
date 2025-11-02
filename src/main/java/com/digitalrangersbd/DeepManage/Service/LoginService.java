package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.JWT.JwtService;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import com.mysql.cj.log.Log;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(String email, String rawPassword){

        if(email != null && rawPassword != null){
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Email not found"));

            if(!passwordEncoder.matches(rawPassword, user.getPassword())){
                throw new RuntimeException("Password does not match");
            }

            String token = jwtService.generateToken(user.getUser_id());

            return token;
        }
        else {
            return "Email or Password cannot be empty";
        }

    }
}

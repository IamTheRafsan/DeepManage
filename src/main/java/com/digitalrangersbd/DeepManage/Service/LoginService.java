package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Dto.LoginDto;
import com.digitalrangersbd.DeepManage.Dto.LoginResponseDto;
import com.digitalrangersbd.DeepManage.Dto.UserResponseDto;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.JWT.JwtService;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import com.mysql.cj.log.Log;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    public LoginResponseDto login(LoginDto dto){

        if(dto.getEmail() != null && dto.getPassword() != null){
            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new RuntimeException("Email not found"));

            if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
                throw new RuntimeException("Password does not match");
            }

            String token = jwtService.generateToken(user.getEmail(), user.getUser_id());

            UserResponseDto safeUser = new UserResponseDto(
                    user.getUser_id(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getGender().name(),
                    String.valueOf(user.getMobile()),
                    user.getCountry(),
                    user.getCity(),
                    user.getAddress(),
                    user.getRole().stream()
                            .map(r -> r.getName())
                            .collect(Collectors.toList())
            );

            return new LoginResponseDto(token, safeUser);
        }
        else {
            throw new RuntimeException("Email or Password cannot be empty");
        }

    }
}
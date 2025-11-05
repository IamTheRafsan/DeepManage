package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.JWT.JwtService;
import com.digitalrangersbd.DeepManage.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try{
            String token = loginService.login(email, password);
            return ResponseEntity.ok(Map.of("token", token));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Login failed"));
        }
    }

}
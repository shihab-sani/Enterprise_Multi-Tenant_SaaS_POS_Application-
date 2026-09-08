package posapplication.multitenantsaas.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import posapplication.multitenantsaas.Dtos.UserDtos;
import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.Payload.Response.AuthResponse;
import posapplication.multitenantsaas.Services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserDtos userDtos) throws UserException {
        AuthResponse authResponse = authService.Register(userDtos);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDtos userDtos) throws UserException {
        AuthResponse authResponse = authService.Login(userDtos);
        return ResponseEntity.ok(authResponse);
    }
}

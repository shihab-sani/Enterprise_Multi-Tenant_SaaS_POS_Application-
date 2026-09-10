package posapplication.multitenantsaas.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posapplication.multitenantsaas.Dtos.UserDtos;
import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.Mapper.UserMapper;
import posapplication.multitenantsaas.ModelClass.User;
import posapplication.multitenantsaas.Services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserDtos> getUserProfile(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.getUserFromToken(token);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/{id}")
     public ResponseEntity<UserDtos> getUserProfile(@PathVariable UUID id) throws UserException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}

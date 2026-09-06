package posapplication.multitenantsaas.Services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posapplication.multitenantsaas.Config.JwtProvider;
import posapplication.multitenantsaas.Dtos.UserDtos;
import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.ModelClass.User;
import posapplication.multitenantsaas.Payload.Response.AuthResponse;
import posapplication.multitenantsaas.Repository.UserRepository;
import posapplication.multitenantsaas.Services.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse Register(UserDtos userDtos) throws UserException {
        User user = userRepository.findByEmail(userDtos.getEmail());
        if (user != null) {
            throw new UserException("User already exists");
        }
        return null;
    }

    @Override
    public AuthResponse Login(UserDtos userDtos) {
        return null;
    }
}

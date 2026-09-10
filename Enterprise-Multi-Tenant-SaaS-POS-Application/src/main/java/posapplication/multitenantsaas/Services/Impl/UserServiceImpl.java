package posapplication.multitenantsaas.Services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import posapplication.multitenantsaas.Config.JwtProvider;
import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.ModelClass.User;
import posapplication.multitenantsaas.Repository.UserRepository;
import posapplication.multitenantsaas.Services.UserService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        if (email == null) {
            throw new UserException("Invalid token");
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(UUID id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

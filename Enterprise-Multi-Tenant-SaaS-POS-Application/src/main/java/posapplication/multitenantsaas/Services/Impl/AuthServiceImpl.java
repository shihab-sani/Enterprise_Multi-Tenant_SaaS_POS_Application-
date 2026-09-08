package posapplication.multitenantsaas.Services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posapplication.multitenantsaas.Config.JwtProvider;
import posapplication.multitenantsaas.Dtos.UserDtos;
import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.Mapper.UserMapper;
import posapplication.multitenantsaas.ModelClass.User;
import posapplication.multitenantsaas.ModelClass.UserRole;
import posapplication.multitenantsaas.Payload.Response.AuthResponse;
import posapplication.multitenantsaas.Repository.UserRepository;
import posapplication.multitenantsaas.Services.AuthService;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;
    private final UserMapper userMapper;

    @Override
    public AuthResponse Register(UserDtos userDtos) throws UserException {
        User user = userRepository.findByEmail(userDtos.getEmail());
        if (user != null) {
            throw new UserException("User already exists");
        }

        if (userDtos.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("Admin Cannot be Created");
        }

        User newUser = new User();
        newUser.setUserName(userDtos.getUserName());
        newUser.setEmail(userDtos.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDtos.getPassword()));
        newUser.setRole(userDtos.getRole());
        newUser.setPhone(userDtos.getPhone());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        newUser.setLoginAt(LocalDateTime.now());
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDtos.getEmail(), userDtos.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User Registered SuccessFully");
        authResponse.setUser(userMapper.toDto(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse Login(UserDtos userDtos) throws UserException {
        String email = userDtos.getEmail();
        String password = userDtos.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);
        User user = userRepository.findByEmail(email);
        user.setLoginAt(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User Logged In SuccessFully");
        authResponse.setUser(userMapper.toDto(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {
        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);

        if (userDetails == null) {
            throw new UserException("User not found with email: " + email);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Invalid password for user: " + email);
        }

        return new UsernamePasswordAuthenticationToken(email,null, userDetails.getAuthorities());
    }
}

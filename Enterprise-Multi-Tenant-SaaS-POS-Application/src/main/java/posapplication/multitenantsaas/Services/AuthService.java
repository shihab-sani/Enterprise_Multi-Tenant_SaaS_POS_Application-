package posapplication.multitenantsaas.Services;

import posapplication.multitenantsaas.Dtos.UserDtos;
import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.Payload.Response.AuthResponse;

public interface AuthService {
    AuthResponse Register(UserDtos userDtos) throws UserException;
    AuthResponse Login(UserDtos userDtos);
}

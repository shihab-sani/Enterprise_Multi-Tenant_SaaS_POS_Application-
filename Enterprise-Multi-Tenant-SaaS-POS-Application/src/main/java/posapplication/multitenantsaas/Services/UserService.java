package posapplication.multitenantsaas.Services;

import posapplication.multitenantsaas.ExceptionHandler.UserException;
import posapplication.multitenantsaas.ModelClass.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserFromToken(String token) throws UserException;
    User getUserById(UUID id) throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getCurrentUser() throws UserException;
    List<User> getAllUsers();
}

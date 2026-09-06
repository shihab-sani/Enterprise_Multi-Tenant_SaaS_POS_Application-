package posapplication.multitenantsaas.ExceptionHandler;

public class UserException extends Throwable {
    public UserException(String userAlreadyExists) {
        super(userAlreadyExists);
    }
}

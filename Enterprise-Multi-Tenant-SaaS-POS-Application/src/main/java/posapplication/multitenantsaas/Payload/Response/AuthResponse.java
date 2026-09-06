package posapplication.multitenantsaas.Payload.Response;

import lombok.Data;
import posapplication.multitenantsaas.Dtos.UserDtos;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDtos user;
}

package posapplication.multitenantsaas.Dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import posapplication.multitenantsaas.ModelClass.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDtos {
    private UUID id;
    private String userName;
    private String email;
    private String phone;
    private UserRole role;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime loginAt;
}

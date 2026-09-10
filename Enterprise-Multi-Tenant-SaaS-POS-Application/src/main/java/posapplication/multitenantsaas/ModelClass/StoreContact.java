package posapplication.multitenantsaas.ModelClass;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Embeddable
public class StoreContact {
    private String phoneNumber;
    @Email(message = "Invalid email format")
    private String email;
    private String address;
}
package posapplication.multitenantsaas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import posapplication.multitenantsaas.ModelClass.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}

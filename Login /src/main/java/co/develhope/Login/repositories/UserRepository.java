package co.develhope.Login.repositories;

import co.develhope.Login.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByPasswordResetCode(String resetPasswordCode);

    boolean existsByEmail(String email);

    User findByActivationCode(String activationCode);
}

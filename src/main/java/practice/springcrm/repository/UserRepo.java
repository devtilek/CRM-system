package practice.springcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.springcrm.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
}

package security.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import security.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}

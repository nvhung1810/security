package security.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import security.security.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("SELECT u FROM User u WHERE u.customer_staff_email = ?1")
  Optional<User> findByCustomerStaffEmail(String customerStaffEmail);
}

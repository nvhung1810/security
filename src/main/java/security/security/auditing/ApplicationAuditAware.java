package security.security.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import security.security.entity.User;

import java.util.Optional;

// Su dung tinh nang auditing trong Spring Data JPA va Spring Security

/**
 * AuditorAware: is interface[Spring Data JPA]. Dung de cung cap thong tin ve ng
 * dung thuc hien hanh dong hien tai. Trong trường hợp này, nó sẽ trả về một
 * Optional<Integer>, trong đó Integer là kiểu dữ liệu của người thực hiện.
 */
public class ApplicationAuditAware implements AuditorAware<Integer> {
  @Override

  /**
   * [public Optional<Integer> getCurrentAuditor() ]
   * Phương thức này được ghi đè từ giao diện AuditorAware. Nhiệm vụ của nó là trả
   * về một Optional<Integer> đại diện cho người thực hiện hành động hiện tại.
   */
  public Optional<Integer> getCurrentAuditor() {
    /**
     * Lấy thông tin xác thực từ SecurityContextHolder. SecurityContextHolder là một
     * lớp giữ thông tin về người dùng đang được xác thực.
     */
    Authentication authentication = SecurityContextHolder
        .getContext()
        .getAuthentication();
    /**
     * Kiểm tra xem người dùng có được xác thực không và không phải là một người
     * dùng ẩn danh (AnonymousAuthenticationToken). Nếu không, phương thức trả về
     * Optional.empty().
     */
    if (authentication == null ||
        !authentication.isAuthenticated() ||
        authentication instanceof AnonymousAuthenticationToken) {
      return Optional.empty();
    }
    /**
     * Nếu người dùng đã được xác thực, lấy thông tin người dùng từ đối tượng
     * Principal và trả về Optional chứa id của người dùng. Phương thức getId() được
     * giả sử là phương thức trả về ID của người dùng.
     */
    User userPrincipal = (User) authentication.getPrincipal();
    return Optional.ofNullable(userPrincipal.getId());
  }
}

/**
 * Tóm lại, lớp ApplicationAuditAware cung cấp một cách để Spring Data JPA biết
 * ai là người thực hiện hành động hiện tại thông qua việc sử dụng thông tin xác
 * thực từ Spring Security.
 */

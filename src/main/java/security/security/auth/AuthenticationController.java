package security.security.auth;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  /**
   *
   * `@PostMapping`: Method handle HTTP POST
   *
   * HttpServletRequest request: Đối tượng chứa thông tin về yêu cầu
   * HTTP, giúp truy cập các thông tin như headers, parameters,
   * cookies, v.v.
   *
   * HttpServletResponse response: Đối tượng cho phép phương thức xử lý gửi phản
   * hồi HTTP trả về client
   */
  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    service.refreshToken(request, response);
  }
}

/**
 * Tóm lại, phương thức refreshToken trong lớp AuthenticationController được sử
 * dụng để xử lý yêu cầu "refresh token" và gọi đến phương thức tương ứng trong
 * đối tượng service để thực hiện quá trình làm mới token.
 */

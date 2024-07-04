package ozzy.project.demo.auth;

import org.aspectj.internal.lang.annotation.ajcPrivileged;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

	private String email;
	private String password;
}

package ozzy.project.todoApp.security.auth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ozzy.project.todoApp.db.entity.user.Role;
import ozzy.project.todoApp.db.entity.user.User;
import ozzy.project.todoApp.db.entity.user.UserRepository;
import ozzy.project.todoApp.security.config.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
				.tasks(null)
				.build();
		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();

	}
}

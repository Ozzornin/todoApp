package ozzy.project.todoApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@GetMapping("")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello world!");
	}

	@GetMapping("/getContext")
	public String getContext() {
		System.out.println("HEEEEEEEEEEEEEEEEEEEEELLLLOOO");
		SecurityContext constext = SecurityContextHolder.getContext();
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}

package ozzy.project.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@GetMapping("")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello world!");
	}

}

package ozzy.project.todoApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ozzy.project.todoApp.db.entity.user.User;
import ozzy.project.todoApp.db.entity.user.UserRepository;
import ozzy.project.todoApp.db.entity.user.UserService;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String getUserById(@RequestParam String id) {
        return userRepository.findById(Integer.parseInt(id)).get().toString();
    }

    @GetMapping("current")
    public Optional<User> getCurrentUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}

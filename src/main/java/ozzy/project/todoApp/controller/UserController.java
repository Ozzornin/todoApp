package ozzy.project.todoApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ozzy.project.todoApp.dto.UserDTO;
import ozzy.project.todoApp.entity.user.User;
import ozzy.project.todoApp.entity.user.UserRepository;
import ozzy.project.todoApp.entity.user.UserService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public UserDTO getUserById(@RequestParam String id) {
        Optional<User> user = userRepository.findById(Integer.parseInt(id));
        return modelMapper.map(user, UserDTO.class);
    }

    @GetMapping("current")
    public UserDTO getCurrentUser() {
        Optional<User> user = userRepository
                .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return modelMapper.map(user, UserDTO.class);
    }

}

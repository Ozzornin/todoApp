package ozzy.project.todoApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ozzy.project.todoApp.db.entity.task.Task;
import ozzy.project.todoApp.db.entity.user.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    TaskController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getTasks() {
        return ResponseEntity.ok(userService.getCurrent().get().getTasks());
    }

    @PostMapping("add")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        userService.addTask(SecurityContextHolder.getContext().getAuthentication().getName(), task);
        return ResponseEntity.ok("Task added");
    }

}

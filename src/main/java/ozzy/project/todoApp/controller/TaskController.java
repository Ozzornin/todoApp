package ozzy.project.todoApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ozzy.project.todoApp.dto.TaskDTO;
import ozzy.project.todoApp.entity.task.Task;
import ozzy.project.todoApp.entity.task.TaskDAO;
import ozzy.project.todoApp.entity.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final UserService userService;
    private final TaskDAO taskDAO;
    private final ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<?> getTasks() {
        List<Task> tasks = userService.getCurrent().get().getTasks();
        List<TaskDTO> tasksDTO = tasks.stream().map(task -> mapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksDTO);
    }

    @PostMapping("add")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        userService.addTask(SecurityContextHolder.getContext().getAuthentication().getName(), task);
        return ResponseEntity.ok("Task added");
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteTask(@RequestParam String id) {
        userService.deleteTask(Integer.parseInt(id));
        return ResponseEntity.ok("Task deleted");
    }

    @PatchMapping("")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO task) {
        taskDAO.update(task);
        return ResponseEntity.ok("Task updated");
    }
}

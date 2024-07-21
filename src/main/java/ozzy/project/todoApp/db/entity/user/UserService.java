package ozzy.project.todoApp.db.entity.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ozzy.project.todoApp.db.entity.task.Task;
import ozzy.project.todoApp.db.entity.task.TaskDAOImpl;

@Service
public class UserService {

    public UserService(UserRepository userRepository, TaskDAOImpl taskDAO) {
        this.userRepository = userRepository;
        this.taskDAO = taskDAO;
    }

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    private TaskDAOImpl taskDAO;

    @Transactional
    public void addTask(String email, Task task) {
        User user = getCurrent().orElseThrow(() -> new RuntimeException("User not found"));
        user.addTask(task);
        taskDAO.add(task);
        logger.info(String.format("Adding task %s to user %s", task.getTitle(), user.getEmail()));
        userRepository.save(user);
    }

    public Optional<User> getCurrent() {
        logger.info("Searching for current user");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email);

    }
}

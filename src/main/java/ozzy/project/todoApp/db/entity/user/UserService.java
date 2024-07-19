package ozzy.project.todoApp.db.entity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ozzy.project.todoApp.db.entity.task.Task;
import ozzy.project.todoApp.db.entity.task.TaskDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    public UserService(UserRepository userRepository, TaskDAOImpl taskDAO) {
        this.userRepository = userRepository;
        this.taskDAO = taskDAO;
    }

    private UserRepository userRepository;

    private TaskDAOImpl taskDAO;

    @Transactional
    public void addTaskToUser(Integer id, Task task) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.addTask(task);
        taskDAO.add(task);
        userRepository.save(user);
    }
}

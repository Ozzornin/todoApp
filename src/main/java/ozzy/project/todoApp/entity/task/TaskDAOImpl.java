package ozzy.project.todoApp.entity.task;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ozzy.project.todoApp.dto.TaskDTO;
import ozzy.project.todoApp.entity.user.User;
import ozzy.project.todoApp.entity.user.UserRepository;

@Repository
@AllArgsConstructor
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    @Transactional
    public void delete(Integer id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null) {
            User user = task.getUser();
            if (user != null) {
                user.getTasks().remove(task);
                userRepository.save(user);
            }
            entityManager.remove(task);
        }
    }

    @Override
    @Transactional
    public void update(TaskDTO task) {
        Task oldTask = entityManager.find(Task.class, task.getId());
        if (oldTask != null) {
            mapper.map(task, oldTask);
        }
        entityManager.merge(oldTask);
    }

    @Override
    public void add(Task task) {
        entityManager.persist(task);
    }

}

package ozzy.project.todoApp.db.entity.task;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ozzy.project.todoApp.db.entity.user.User;
import ozzy.project.todoApp.db.entity.user.UserRepository;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    private final UserRepository userRepository;

    public TaskDAOImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

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
    public void update(Task task) {
        entityManager.merge(task);
    }

    @Override
    public void add(Task task) {
        entityManager.persist(task);
    }

}

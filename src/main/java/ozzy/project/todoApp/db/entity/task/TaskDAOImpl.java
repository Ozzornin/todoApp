package ozzy.project.todoApp.db.entity.task;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void delete(Task task) {
        entityManager.remove(task);
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

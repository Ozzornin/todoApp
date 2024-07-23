package ozzy.project.todoApp.db.entity.task;

public interface TaskDAO {

    public void delete(Integer id);

    public void update(Task task);

    public void add(Task task);
}

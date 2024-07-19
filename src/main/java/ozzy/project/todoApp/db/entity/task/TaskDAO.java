package ozzy.project.todoApp.db.entity.task;

public interface TaskDAO {

    public void delete(Task task);

    public void update(Task task);

    public void add(Task task);
}

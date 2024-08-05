package ozzy.project.todoApp.entity.task;

import ozzy.project.todoApp.dto.TaskDTO;

public interface TaskDAO {

    public void delete(Integer id);

    public void update(TaskDTO task);

    public void add(Task task);
}

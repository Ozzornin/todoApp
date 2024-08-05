package ozzy.project.todoApp.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private Integer id;

    private String title;

    private String description;

    private Boolean completed;
}

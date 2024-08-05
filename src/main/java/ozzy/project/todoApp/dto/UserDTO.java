package ozzy.project.todoApp.dto;

import lombok.Data;
import ozzy.project.todoApp.entity.user.Role;

@Data
public class UserDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}

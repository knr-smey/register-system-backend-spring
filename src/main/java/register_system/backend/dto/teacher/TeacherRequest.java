package register_system.backend.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotNull(message = "role_id is required")
    private Integer role_id;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "status is required")
    private String status;
}

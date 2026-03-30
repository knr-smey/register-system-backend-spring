package register_system.backend.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {

    private Long id;
    private String name;
    private String email;
    private Integer role_id;
    private String phone;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

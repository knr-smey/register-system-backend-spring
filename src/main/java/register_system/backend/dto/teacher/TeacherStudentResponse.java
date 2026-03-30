package register_system.backend.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherStudentResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
}

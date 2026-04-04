package register_system.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private String stu_name;
    private String tel;
    private String gender;
    private Long course_id;
    private Long class_id;
}
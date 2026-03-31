package register_system.backend.dto;


import java.time.LocalDate;

import lombok.Data;
import register_system.backend.Enum.stausStudent;
@Data

public class dtoResponse {
    private long id;
    private long course_id;
    private long teacher_id;        
    private LocalDate start_date;
    private int max_students;
    private stausStudent status;
    private LocalDate created_at;
    private LocalDate updated_at;
}

package register_system.backend.dto;


import java.time.LocalDate;

import lombok.Data;
import register_system.backend.enums.stausStudent;
@Data
public class dtoRequest {
    private long course_id;
    private long teacher_id;        
    private LocalDate start_date;
    private int max_students;
    private stausStudent status;
}

package register_system.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentRequestDTO {
    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("class_id")
    private Long classId;

    @JsonProperty("enroll_date")
    private LocalDate enrollDate;

    private String status;
}

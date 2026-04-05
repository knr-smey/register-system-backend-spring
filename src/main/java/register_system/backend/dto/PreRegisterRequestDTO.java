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
public class PreRegisterRequestDTO {
    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("course_id")
    private Long courseId;
    
    @JsonProperty("request_date")
    private LocalDate requestDate;

    @JsonProperty("image_url")
    private String imageUrl;

    private String status;
}

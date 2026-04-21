package register_system.backend.dto.ScheduleDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequest {

    @NotNull(message = "class_id is required")
    @JsonProperty("class_id")
    private Long classId;

    @NotBlank(message = "shift is required")
    @Pattern(regexp = "(?i)^(Day|Weekend)$", message = "shift must be Day or Weekend")
    private String shift;

    @NotNull(message = "start_time is required")
    @JsonProperty("start_time")
    private LocalTime startTime;

    @NotNull(message = "end_time is required")
    @JsonProperty("end_time")
    private LocalTime endTime;
}


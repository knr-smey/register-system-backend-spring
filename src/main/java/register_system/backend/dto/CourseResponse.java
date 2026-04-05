package register_system.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private String image;
    private BigDecimal price;
    private Integer duration;
    private Long created_by;
    private Long sub_category_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

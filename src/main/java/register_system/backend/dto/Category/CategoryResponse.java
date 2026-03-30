package register_system.backend.dto.Category;
 
import lombok.*;
import java.time.LocalDateTime;
 
public class CategoryResponse {
 
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Detail {
        private Long id;
        private String name;
        private String description;
        private Long createdBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
 
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Summary {
        private Long id;
        private String name;
        private String description;
    }
}
 
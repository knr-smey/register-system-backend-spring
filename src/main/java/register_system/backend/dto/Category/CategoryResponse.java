package register_system.backend.dto.Category;
 
import com.fasterxml.jackson.annotation.JsonProperty;
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
 
        @JsonProperty("created_by")
        private Long createdBy;
 
        @JsonProperty("created_at")
        private LocalDateTime createdAt;
 
        @JsonProperty("updated_at")
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
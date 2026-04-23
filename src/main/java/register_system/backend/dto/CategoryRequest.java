package register_system.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
 
public class CategoryRequest {
 
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
 
        private String name;
 
        private String description;
 
        @JsonProperty("created_by")
        private Long createdBy;
    }
 
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Update {
 
        private String name;
 
        private String description;
    }
}
package register_system.backend.dto.Category;

import lombok.*;
 
public class CategoryRequest {
 
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
        private String name;
        private String description;
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
 
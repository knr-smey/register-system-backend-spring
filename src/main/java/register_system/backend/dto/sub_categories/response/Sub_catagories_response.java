package register_system.backend.dto.sub_categories.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for sub-category data")
public class Sub_catagories_response {
    
    @Schema(description = "Sub-category ID", example = "1")
    private Long id;
    
    @Schema(description = "Sub-category name", example = "Electronics")
    private String name;
    
    @Schema(description = "Parent category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;
    
    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}
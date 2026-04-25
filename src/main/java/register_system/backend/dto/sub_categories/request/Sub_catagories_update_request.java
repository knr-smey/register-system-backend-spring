package register_system.backend.dto.sub_categories.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing sub-category")
public class Sub_catagories_update_request {
    
    @NotBlank(message = "Sub-category name is required")
    @Schema(description = "Updated name of the sub-category", example = "Consumer Electronics")
    private String name;
    
    @Schema(description = "Updated category ID", example = "2")
    private Long categoryId;
}
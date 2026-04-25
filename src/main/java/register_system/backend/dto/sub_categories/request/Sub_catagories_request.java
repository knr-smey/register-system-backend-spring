package register_system.backend.dto.sub_categories.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new sub-category")
public class Sub_catagories_request {
    
    @NotBlank(message = "Sub-category name is required")
    @Schema(description = "Name of the sub-category", example = "Electronics")
    private String name;
    
    @NotNull(message = "Category ID is required")
    @Schema(description = "ID of the parent category", example = "1")
    private Long categoryId;
}
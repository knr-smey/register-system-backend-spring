package register_system.backend.dto.sub_categories.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for querying sub-categories with filters")
public class Sub_catagories_query_request {
    
    @Schema(description = "Filter by sub-category name (partial match)", example = "Electro")
    private String name;
    
    @Schema(description = "Filter by category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Page number (0-indexed)", example = "0")
    private Integer page = 0;
    
    @Schema(description = "Number of items per page", example = "10")
    private Integer size = 10;
    
    @Schema(description = "Sort field", example = "id")
    private String sortBy = "id";
    
    @Schema(description = "Sort direction (asc/desc)", example = "asc")
    private String sortDir = "asc";
}
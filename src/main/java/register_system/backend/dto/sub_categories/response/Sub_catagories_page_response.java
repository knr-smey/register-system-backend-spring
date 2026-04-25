package register_system.backend.dto.sub_categories.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Paginated response for sub-categories")
public class Sub_catagories_page_response {
    
    @Schema(description = "List of sub-categories")
    private List<Sub_catagories_response> content;
    
    @Schema(description = "Current page number")
    private int pageNumber;
    
    @Schema(description = "Number of items per page")
    private int pageSize;
    
    @Schema(description = "Total number of elements")
    private long totalElements;
    
    @Schema(description = "Total number of pages")
    private int totalPages;
    
    @Schema(description = "Is this the last page")
    private boolean last;
}
package register_system.backend.dto.PromotionDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Discount percent is required")
    @DecimalMin(value = "0.01", message = "Discount must be greater than 0")
    @DecimalMax(value = "100.00", message = "Discount must not exceed 100")
    @JsonProperty("discount_percent")
    private BigDecimal discountPercent;

    @NotNull(message = "Start date is required")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @JsonProperty("end_date")
    private LocalDate endDate;
}
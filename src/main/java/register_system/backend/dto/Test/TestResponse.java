package register_system.backend.dto.Test;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class TestResponse {
    private Long id;
    private String testName;
}
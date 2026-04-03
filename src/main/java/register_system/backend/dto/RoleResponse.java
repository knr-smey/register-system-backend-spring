package register_system.backend.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;
}
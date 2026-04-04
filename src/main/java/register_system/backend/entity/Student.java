package register_system.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stuName;
    private String tel;
    private String gender;

    private Long courseId;
    private Long classId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package register_system.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import register_system.backend.enums.stausStudent;

@Entity
@Table(name = "class_models")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class classModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long course_id;
    private long teacher_id;

    private LocalDate start_date; // date only

    private int max_students;
    private stausStudent status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at; // auto-fill when inserted

    @UpdateTimestamp
    private LocalDateTime updated_at; // auto-update on update
}
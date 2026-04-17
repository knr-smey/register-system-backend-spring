package register_system.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import register_system.backend.model.Enrollment;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByClassId(Long classId);
    boolean existsByStudentIdAndClassId(Long studentId, Long classId);
}

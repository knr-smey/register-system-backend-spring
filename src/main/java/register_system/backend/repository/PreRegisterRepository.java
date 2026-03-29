package register_system.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import register_system.backend.model.PreRegister;

import java.util.List;

@Repository
public interface PreRegisterRepository extends JpaRepository<PreRegister, Long> {
    List<PreRegister> findByStudentId(Long studentId);
    List<PreRegister> findByCourseId(Long courseId);
}

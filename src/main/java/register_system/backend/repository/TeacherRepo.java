package register_system.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import register_system.backend.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);
}

package register_system.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import register_system.backend.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

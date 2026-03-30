package register_system.backend.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import register_system.backend.model.course.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

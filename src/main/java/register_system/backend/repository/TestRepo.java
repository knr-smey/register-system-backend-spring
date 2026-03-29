package register_system.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import register_system.backend.model.Test;

public interface TestRepo extends JpaRepository<Test, Long> {
}
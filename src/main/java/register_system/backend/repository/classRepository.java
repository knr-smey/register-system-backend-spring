package register_system.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import register_system.backend.model.classModels;

public interface classRepository extends JpaRepository<classModels, Long> {
}
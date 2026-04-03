package register_system.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import register_system.backend.model.RoleModel;

public interface  RoleRepository extends JpaRepository<RoleModel, Long>{}


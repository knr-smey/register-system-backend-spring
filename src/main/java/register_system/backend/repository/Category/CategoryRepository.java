package register_system.backend.repository.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import register_system.backend.model.category.CategoryModel;

import java.util.List;
import java.util.Optional;

 
@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
 
    List<CategoryModel> findAllByOrderByCreatedAtDesc();
 
    Optional<CategoryModel> findByName(String name);
 
    boolean existsByName(String name);
 
    List<CategoryModel> findByCreatedBy(Long createdBy);
}
 
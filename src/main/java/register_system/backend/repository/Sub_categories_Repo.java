package register_system.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import register_system.backend.entity.Sub_categories_model;

import java.util.List;
import java.util.Optional;

@Repository
public interface Sub_categories_Repo extends JpaRepository<Sub_categories_model, Long> {
    
    Optional<Sub_categories_model> findByNameAndCategoryId(String name, Long categoryId);
    
    boolean existsByNameAndCategoryId(String name, Long categoryId);
    
    List<Sub_categories_model> findByCategoryId(Long categoryId);
    
    Page<Sub_categories_model> findByCategoryId(Long categoryId, Pageable pageable);
    
    @Query("SELECT s FROM Sub_categories_model s WHERE " +
           "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:categoryId IS NULL OR s.categoryId = :categoryId)")
    Page<Sub_categories_model> searchSubCategories(@Param("name") String name,
                                                     @Param("categoryId") Long categoryId,
                                                     Pageable pageable);
}
package register_system.backend.service;

import register_system.backend.dto.CategoryRequest;
import register_system.backend.dto.CategoryResponse;
import register_system.backend.mapper.CategoryMapper;
import register_system.backend.model.CategoryModel;
import register_system.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse.Detail create(CategoryRequest.Create request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category with name '" + request.getName() + "' already exists");
        }
        
        CategoryModel category = categoryMapper.toEntity(request);
        return categoryMapper.toDetail(categoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    public CategoryResponse.Detail getById(Long id) {
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toDetail(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse.Detail> getAll() {
        List<CategoryModel> categories = categoryRepository.findAllByOrderByCreatedAtDesc();
        return categoryMapper.toDetailList(categories);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse.Summary> getAllSummary() {
        List<CategoryModel> categories = categoryRepository.findAllByOrderByCreatedAtDesc();
        return categoryMapper.toSummaryList(categories);
    }

    public CategoryResponse.Detail update(Long id, CategoryRequest.Update request) {
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        // Validation for unique name if the name is being changed
        if (request.getName() != null
                && !request.getName().equals(category.getName())
                && categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category with name '" + request.getName() + "' already exists");
        }

        categoryMapper.updateEntity(category, request);
        return categoryMapper.toDetail(categoryRepository.save(category));
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
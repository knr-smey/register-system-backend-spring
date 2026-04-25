package register_system.backend.service.Sub_categories;  // Fixed package (should be impl, not Sub_categories)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import register_system.backend.dto.sub_categories.request.Sub_catagories_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_update_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_query_request;
import register_system.backend.dto.sub_categories.response.Sub_catagories_response;
import register_system.backend.dto.sub_categories.response.Sub_catagories_page_response;
import register_system.backend.exception.subcatagory.DuplicateSubCategoryException;
import register_system.backend.exception.subcatagory.ResourceNotFoundException;  // Import missing exception
import register_system.backend.mapper.Sub_catagories_Mapper;
import register_system.backend.entity.Sub_categories_model;
import register_system.backend.repository.Sub_categories_Repo;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class Sub_CategoryServiceImpl implements Sub_CategoryService {
    
    @Autowired
    private Sub_categories_Repo subCategoriesRepo;
    
    @Autowired
    private Sub_catagories_Mapper mapper;
    
    @Override
    public Sub_catagories_response createSubCategory(Sub_catagories_request request) {
        // Check for duplicate
        if (subCategoriesRepo.existsByNameAndCategoryId(request.getName(), request.getCategoryId())) {
            throw new DuplicateSubCategoryException(request.getName(), request.getCategoryId());
        }
        
        Sub_categories_model entity = mapper.toEntity(request);
        Sub_categories_model savedEntity = subCategoriesRepo.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public Sub_catagories_response getSubCategoryById(Long id) {
        Sub_categories_model entity = subCategoriesRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sub-category", id));
        return mapper.toResponse(entity);
    }
    
    @Override
    public List<Sub_catagories_response> getAllSubCategories() {
        return subCategoriesRepo.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public Sub_catagories_response updateSubCategory(Long id, Sub_catagories_update_request request) {
        Sub_categories_model entity = subCategoriesRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sub-category", id));
        
        // Check for duplicate if name or categoryId changed
        if (request.getName() != null && !request.getName().equals(entity.getName()) ||
            request.getCategoryId() != null && !request.getCategoryId().equals(entity.getCategoryId())) {
            
            String newName = request.getName() != null ? request.getName() : entity.getName();
            Long newCategoryId = request.getCategoryId() != null ? request.getCategoryId() : entity.getCategoryId();
            
            if (subCategoriesRepo.existsByNameAndCategoryId(newName, newCategoryId)) {
                throw new DuplicateSubCategoryException(newName, newCategoryId);
            }
        }
        
        mapper.updateEntity(entity, request);
        Sub_categories_model updatedEntity = subCategoriesRepo.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void deleteSubCategory(Long id) {
        if (!subCategoriesRepo.existsById(id)) {
            throw new ResourceNotFoundException("Sub-category", id);
        }
        subCategoriesRepo.deleteById(id);
    }
    
    @Override
    public Sub_catagories_page_response searchSubCategories(Sub_catagories_query_request queryRequest) {
        Sort.Direction direction = queryRequest.getSortDir().equalsIgnoreCase("desc") 
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(
                queryRequest.getPage(),
                queryRequest.getSize(),
                Sort.by(direction, queryRequest.getSortBy())
        );
        
        Page<Sub_categories_model> page = subCategoriesRepo.searchSubCategories(
                queryRequest.getName(),
                queryRequest.getCategoryId(),
                pageable
        );
        
        List<Sub_catagories_response> content = page.getContent()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        
        return new Sub_catagories_page_response(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
    
    @Override
    public List<Sub_catagories_response> getSubCategoriesByCategoryId(Long categoryId) {
        return subCategoriesRepo.findByCategoryId(categoryId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByNameAndCategoryId(String name, Long categoryId) {
        return subCategoriesRepo.existsByNameAndCategoryId(name, categoryId);
    }
}
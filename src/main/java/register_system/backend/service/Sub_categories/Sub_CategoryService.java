package register_system.backend.service.Sub_categories;

import register_system.backend.dto.sub_categories.request.Sub_catagories_query_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_update_request;
import register_system.backend.dto.sub_categories.response.Sub_catagories_page_response;
import register_system.backend.dto.sub_categories.response.Sub_catagories_response;

import java.util.List;

public interface Sub_CategoryService {
    
    Sub_catagories_response createSubCategory(Sub_catagories_request request);
    
    Sub_catagories_response getSubCategoryById(Long id);
    
    List<Sub_catagories_response> getAllSubCategories();
    
    Sub_catagories_response updateSubCategory(Long id, Sub_catagories_update_request request);
    
    void deleteSubCategory(Long id);
    
    Sub_catagories_page_response searchSubCategories(Sub_catagories_query_request queryRequest);
    
    List<Sub_catagories_response> getSubCategoriesByCategoryId(Long categoryId);
    
    boolean existsByNameAndCategoryId(String name, Long categoryId);
}
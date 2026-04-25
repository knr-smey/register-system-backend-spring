package register_system.backend.mapper;

import org.springframework.stereotype.Component;

import register_system.backend.dto.sub_categories.request.Sub_catagories_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_update_request;
import register_system.backend.dto.sub_categories.response.Sub_catagories_response;
import register_system.backend.entity.Sub_categories_model;

@Component
public class Sub_catagories_Mapper {
    
    public Sub_categories_model toEntity(Sub_catagories_request request) {
        if (request == null) {
            return null;
        }
        
        Sub_categories_model entity = new Sub_categories_model();
        entity.setName(request.getName());
        entity.setCategoryId(request.getCategoryId());
        return entity;
    }
    
    public Sub_categories_model updateEntity(Sub_categories_model entity, Sub_catagories_update_request request) {
        if (request == null) {
            return entity;
        }
        
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getCategoryId() != null) {
            entity.setCategoryId(request.getCategoryId());
        }
        return entity;
    } 
    
    public Sub_catagories_response toResponse(Sub_categories_model entity) {
        if (entity == null) {
            return null;
        }
        
        return new Sub_catagories_response(
            entity.getId(),
            entity.getName(),
            entity.getCategoryId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
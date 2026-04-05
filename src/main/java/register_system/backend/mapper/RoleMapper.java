package register_system.backend.mapper;

import register_system.backend.dto.RoleRequest;
import register_system.backend.dto.RoleResponse;
import register_system.backend.model.RoleModel;

public class RoleMapper {
    public static RoleModel toEntity(RoleRequest dto){
        RoleModel model = new RoleModel();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        return model;
    }

    public static RoleResponse toResponse(RoleModel model){
        RoleResponse response = new RoleResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setDescription(model.getDescription());
        response.setCreated_at(model.getCreated_at());
        response.setUpdated_at(model.getUpdated_at());
        return response;
    }

    public static void updateEntity(RoleModel model, RoleRequest dto){
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
    }
}


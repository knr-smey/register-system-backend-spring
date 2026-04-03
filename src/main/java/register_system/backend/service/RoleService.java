package register_system.backend.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import register_system.backend.dto.RoleRequest;
import register_system.backend.dto.RoleResponse;
import register_system.backend.mapper.RoleMapper;
import register_system.backend.model.RoleModel;
import register_system.backend.repository.RoleRepository;

@Service
public class RoleService {
    public final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponse create(RoleRequest dto) throws Exception {
        RoleModel roleModel = RoleMapper.toEntity(dto);
        roleRepository.save(roleModel);
        return RoleMapper.toResponse(roleModel);
    }

    public List<RoleResponse> getAll() {
        List<RoleModel> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RoleResponse getById(Long id) {
        RoleModel role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return RoleMapper.toResponse(role);
    }

    public RoleResponse update(Long id, RoleRequest dto) throws Exception {
        RoleModel role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        RoleMapper.updateEntity(role, dto);

        roleRepository.save(role);
        return RoleMapper.toResponse(role);
    }

    public void delete(Long id) throws IOException{

        RoleModel role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        roleRepository.delete(role);
    }
}

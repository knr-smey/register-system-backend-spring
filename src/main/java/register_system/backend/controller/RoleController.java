package register_system.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import register_system.backend.dto.RoleRequest;
import register_system.backend.dto.RoleResponse;
import register_system.backend.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public RoleResponse create(@RequestBody RoleRequest dto) throws Exception {
        return roleService.create(dto); // ✅ correct
    }


    @GetMapping
    public List<RoleResponse> getall(){
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public RoleResponse getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    //update
    @PutMapping(value="/{id}", consumes = "multipart/form-data")
    public RoleResponse update(@PathVariable Long id, @RequestBody RoleRequest dto) throws Exception {
        return roleService.update(id, dto);
    }

    //delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws IOException {
        roleService.delete(id);
        return "Role with id " + id + " has been deleted.";
    }
}

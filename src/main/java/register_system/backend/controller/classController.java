package register_system.backend.controller;
import java.io.IOException;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import register_system.backend.dto.dtoRequest;
import register_system.backend.dto.dtoResponse;
import register_system.backend.service.classService;
@CrossOrigin(origins = "*")
@RestController
public class classController {
    private final classService classService;
    public classController(classService classService) {
        this.classService = classService;
    }
    @PostMapping(value="/api/class/post" , consumes="multipart/form-data")
    public dtoResponse createClass(@RequestBody dtoRequest dtoRequest) throws IOException{
        return classService.createClass(dtoRequest);
    }
    @GetMapping(value="/api/class/get")
    public List<dtoResponse> getAll(){
        return classService.getAll();
    }
    @GetMapping(value="/api/class/get/{id}")
    public dtoResponse getById(@PathVariable Long id){
        return classService.getById(id);
    }
    @DeleteMapping(value="/api/class/delete/{id}")
    public dtoResponse delete(@PathVariable Long id){
        return classService.delete(id);
    }
    @PutMapping(value="/api/class/update/{id}" , consumes="multipart/form-data")
    public dtoResponse updateById(@PathVariable Long id, @RequestBody dtoRequest dtoRequest) throws IOException{
        return classService.updateById(id, dtoRequest);
    }
}

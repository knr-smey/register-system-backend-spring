package register_system.backend.controller.category ;


import register_system.backend.dto.Category.CategoryRequest;
import register_system.backend.dto.Category.CategoryResponse;
import register_system.backend.service.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryCourseController {
 
    private final CategoryService categoryService;
 
    @PostMapping
    public ResponseEntity<CategoryResponse.Detail> create(@RequestBody CategoryRequest.Create request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse.Detail> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }
 
    @GetMapping
    public ResponseEntity<List<CategoryResponse.Detail>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
 
    @GetMapping("/summary")
    public ResponseEntity<List<CategoryResponse.Summary>> getAllSummary() {
        return ResponseEntity.ok(categoryService.getAllSummary());
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse.Detail> update(
            @PathVariable Long id,
            @RequestBody CategoryRequest.Update request) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
 
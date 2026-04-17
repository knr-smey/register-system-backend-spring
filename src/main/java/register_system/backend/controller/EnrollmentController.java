package register_system.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import register_system.backend.dto.EnrollmentRequestDTO;
import register_system.backend.dto.EnrollmentResponseDTO;
import register_system.backend.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> create(@RequestBody EnrollmentRequestDTO request) {
        return ResponseEntity.ok(enrollmentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody EnrollmentRequestDTO request) {
        return ResponseEntity.ok(enrollmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getByStudentId(studentId));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByClassId(@PathVariable Long classId) {
        return ResponseEntity.ok(enrollmentService.getByClassId(classId));
    }
}

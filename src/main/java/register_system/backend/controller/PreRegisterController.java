package register_system.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import register_system.backend.dto.PreRegisterRequestDTO;
import register_system.backend.dto.PreRegisterResponseDTO;
import register_system.backend.service.PreRegisterService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pre-registers")
@RequiredArgsConstructor
public class PreRegisterController {

    private final PreRegisterService service;

    @GetMapping
    public ResponseEntity<List<PreRegisterResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreRegisterResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create pre-registration with image upload")
    public ResponseEntity<PreRegisterResponseDTO> create(
            @RequestParam("student_id") Long studentId,
            @RequestParam("course_id") Long courseId,
            @RequestParam(value = "request_date", required = false) LocalDate requestDate,
            @RequestParam(value = "status", required = false) String status,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        
        String imageUrl = service.saveFile(image);
        
        PreRegisterRequestDTO dto = PreRegisterRequestDTO.builder()
                .studentId(studentId)
                .courseId(courseId)
                .requestDate(requestDate)
                .status(status)
                .imageUrl(imageUrl)
                .build();
                
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update pre-registration with image upload")
    public ResponseEntity<PreRegisterResponseDTO> update(
            @PathVariable Long id,
            @RequestParam("student_id") Long studentId,
            @RequestParam("course_id") Long courseId,
            @RequestParam(value = "request_date", required = false) LocalDate requestDate,
            @RequestParam(value = "status", required = false) String status,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        
        String imageUrl = service.saveFile(image);
        
        PreRegisterRequestDTO dto = PreRegisterRequestDTO.builder()
                .studentId(studentId)
                .courseId(courseId)
                .requestDate(requestDate)
                .status(status)
                .imageUrl(imageUrl)
                .build();
                
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

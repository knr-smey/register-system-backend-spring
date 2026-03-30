package register_system.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import register_system.backend.dto.teacher.TeacherClassResponse;
import register_system.backend.dto.teacher.TeacherRequest;
import register_system.backend.dto.teacher.TeacherResponse;
import register_system.backend.dto.teacher.TeacherStudentResponse;
import register_system.backend.service.TeacherService;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService userService;

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@Valid @RequestBody TeacherRequest request) {
        TeacherResponse response = userService.createTeacher(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        return ResponseEntity.ok(userService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getTeacherById(id));
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<List<TeacherClassResponse>> getTeacherClasses(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getTeacherClasses(id));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<TeacherStudentResponse>> getTeacherStudents(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getTeacherStudents(id));
    }
}

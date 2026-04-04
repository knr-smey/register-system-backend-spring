package register_system.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import register_system.backend.dto.StudentDTO;
import register_system.backend.entity.Student;
import register_system.backend.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }
}
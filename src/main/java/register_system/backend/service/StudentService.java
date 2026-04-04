package register_system.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import register_system.backend.entity.Student;
import register_system.backend.repository.StudentRepository;

import java.time.LocalDateTime;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(register_system.backend.dto.StudentDTO dto) {

        Student student = new Student();

        student.setStuName(dto.getStu_name());
        student.setTel(dto.getTel());
        student.setGender(dto.getGender());
        student.setCourseId(dto.getCourse_id());
        student.setClassId(dto.getClass_id());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(student);
    }
}
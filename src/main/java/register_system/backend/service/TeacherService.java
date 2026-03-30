package register_system.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import register_system.backend.dto.teacher.TeacherClassResponse;
import register_system.backend.dto.teacher.TeacherRequest;
import register_system.backend.dto.teacher.TeacherResponse;
import register_system.backend.dto.teacher.TeacherStudentResponse;
import register_system.backend.mapper.TeacherMapper;
import register_system.backend.model.Teacher;
import register_system.backend.repository.TeacherRepo;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

    public static final int ROLE_TEACHER = 2;

    private final TeacherRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public TeacherResponse createTeacher(TeacherRequest req) {
        if (!Integer.valueOf(ROLE_TEACHER).equals(req.getRole_id())) {
            throw new IllegalArgumentException("role_id must be " + ROLE_TEACHER);
        }

        if (!req.getEmail().endsWith("@school.edu")) {
            throw new IllegalArgumentException("teacher email must end with @school.edu");
        }

        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        Teacher user = TeacherMapper.toEntity(req);
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        Teacher saved = userRepo.save(user);
        return TeacherMapper.toResponse(saved);
    }

    public TeacherResponse getTeacherById(Long id) {
        Teacher user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        if (!Integer.valueOf(ROLE_TEACHER).equals(user.getRoleId())) {
            throw new IllegalArgumentException("User is not teacher");
        }
        return TeacherMapper.toResponse(user);
    }

    public List<TeacherResponse> getAllTeachers() {
        return userRepo.findAll().stream()
                .filter(u -> Integer.valueOf(ROLE_TEACHER).equals(u.getRoleId()))
                .map(TeacherMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TeacherClassResponse> getTeacherClasses(Long teacherId) {
        TeacherResponse teacher = getTeacherById(teacherId); // validate teacher exists
        return List.of(
                TeacherClassResponse.builder().id(1L).name("Math 101").schedule("Mon-Wed-Fri 08:00-09:30").build(),
                TeacherClassResponse.builder().id(2L).name("Physics 102").schedule("Tue-Thu 10:00-11:30").build()
        );
    }

    public List<TeacherStudentResponse> getTeacherStudents(Long teacherId) {
        TeacherResponse teacher = getTeacherById(teacherId); // validate teacher exists
        return List.of(
                TeacherStudentResponse.builder().id(100L).name("Student A").email("student.a@school.edu").phone("012345001").build(),
                TeacherStudentResponse.builder().id(101L).name("Student B").email("student.b@school.edu").phone("012345002").build()
        );
    }
}

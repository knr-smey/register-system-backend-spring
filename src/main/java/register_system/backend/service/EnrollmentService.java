package register_system.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import register_system.backend.dto.EnrollmentRequestDTO;
import register_system.backend.dto.EnrollmentResponseDTO;
import register_system.backend.mapper.EnrollmentMapper;
import register_system.backend.model.Enrollment;
import register_system.backend.repository.EnrollmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public List<EnrollmentResponseDTO> getAll() {
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentResponseDTO getById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    @Transactional
    public EnrollmentResponseDTO create(EnrollmentRequestDTO request) {
        Enrollment enrollment = enrollmentMapper.toEntity(request);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDTO(saved);
    }

    @Transactional
    public EnrollmentResponseDTO update(Long id, EnrollmentRequestDTO request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        enrollmentMapper.updateEntity(enrollment, request);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        enrollmentRepository.delete(enrollment);
    }

    public List<EnrollmentResponseDTO> getByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(enrollmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EnrollmentResponseDTO> getByClassId(Long classId) {
        return enrollmentRepository.findByClassId(classId).stream()
                .map(enrollmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}

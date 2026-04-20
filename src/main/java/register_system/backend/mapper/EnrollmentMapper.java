package register_system.backend.mapper;

import org.springframework.stereotype.Component;
import register_system.backend.dto.EnrollmentRequestDTO;
import register_system.backend.dto.EnrollmentResponseDTO;
import register_system.backend.enums.EnrollmentStatus;
import register_system.backend.model.Enrollment;

@Component
public class EnrollmentMapper {

    public EnrollmentResponseDTO toResponseDTO(Enrollment entity) {
        if (entity == null) {
            return null;
        }
        return EnrollmentResponseDTO.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .classId(entity.getClassId())
                .enrollDate(entity.getEnrollDate())
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public Enrollment toEntity(EnrollmentRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(dto.getStudentId());
        enrollment.setClassId(dto.getClassId());
        enrollment.setEnrollDate(dto.getEnrollDate());

        if (dto.getStatus() != null) {
            try {
                enrollment.setStatus(EnrollmentStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                enrollment.setStatus(EnrollmentStatus.ACTIVE);
            }
        } else {
            enrollment.setStatus(EnrollmentStatus.ACTIVE);
        }

        return enrollment;
    }

    public void updateEntity(Enrollment entity, EnrollmentRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        if (dto.getStudentId() != null) {
            entity.setStudentId(dto.getStudentId());
        }
        if (dto.getClassId() != null) {
            entity.setClassId(dto.getClassId());
        }
        if (dto.getEnrollDate() != null) {
            entity.setEnrollDate(dto.getEnrollDate());
        }
        if (dto.getStatus() != null) {
            try {
                entity.setStatus(EnrollmentStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setStatus(EnrollmentStatus.ACTIVE);
            }
        }
    }
}

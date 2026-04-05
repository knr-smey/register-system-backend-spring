package register_system.backend.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import register_system.backend.dto.PreRegisterRequestDTO;
import register_system.backend.dto.PreRegisterResponseDTO;
import register_system.backend.enums.RegistrationStatus;
import register_system.backend.model.PreRegister;

@Component
public class PreRegisterMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PreRegisterResponseDTO toResponseDTO(PreRegister entity) {
        if (entity == null) {
            return null;
        }
        return PreRegisterResponseDTO.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .courseId(entity.getCourseId())
                .requestDate(entity.getRequestDate())
                .imageUrl(entity.getImageUrl())
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().format(formatter) : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(formatter) : null)
                .build();
    }

    public PreRegister toEntity(PreRegisterRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        PreRegister entity = PreRegister.builder()
                .studentId(dto.getStudentId())
                .courseId(dto.getCourseId())
                .requestDate(dto.getRequestDate())
                .imageUrl(dto.getImageUrl())
                .build();

        if (dto.getStatus() != null) {
            try {
                entity.setStatus(RegistrationStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setStatus(RegistrationStatus.PENDING);
            }
        }

        return entity;
    }
}

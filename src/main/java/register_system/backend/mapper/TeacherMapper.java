package register_system.backend.mapper;

import register_system.backend.dto.teacher.TeacherRequest;
import register_system.backend.dto.teacher.TeacherResponse;
import register_system.backend.model.Teacher;

public class TeacherMapper {

    public static Teacher toEntity(TeacherRequest request) {
        return Teacher.builder()
                .name(request.getName())
                .email(request.getEmail())
                .roleId(request.getRole_id())
                .phone(request.getPhone())
                .status(request.getStatus())
                .build();
    }

    public static TeacherResponse toResponse(Teacher user) {
        return TeacherResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role_id(user.getRoleId())
                .phone(user.getPhone())
                .status(user.getStatus())
                .created_at(user.getCreatedAt())
                .updated_at(user.getUpdatedAt())
                .build();
    }
}

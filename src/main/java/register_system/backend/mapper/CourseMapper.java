package register_system.backend.mapper;

import register_system.backend.dto.CourseRequest;
import register_system.backend.dto.CourseResponse;
import register_system.backend.model.Course;

public class CourseMapper {

    private CourseMapper(){}

    // convert dto to entity
    public static Course toEntity(CourseRequest dto, String imageFileName){
        if(dto == null) return null;

        return Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .image(imageFileName)
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .created_by(dto.getCreated_by())
                .sub_category_id(dto.getSub_category_id())
                .build();
    }

    // convert db to response
    public static CourseResponse toResponse(Course course) {
        if(course == null) return null;

        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .image(course.getImage())
                .price(course.getPrice())
                .duration(course.getDuration())
                .created_by(course.getCreated_by())
                .sub_category_id(course.getSub_category_id())
                .created_at(course.getCreated_at())
                .updated_at(course.getUpdated_at())
                .build();
    }

    // for update from client
    public static void updateEntity(Course course, CourseRequest dto, String imageFileName){
        if(course == null || dto == null) return;

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setCreated_by(dto.getCreated_by());
        course.setSub_category_id(dto.getSub_category_id());
        if(imageFileName != null && !imageFileName.isBlank()) {
            course.setImage(imageFileName);
        }
    }
}

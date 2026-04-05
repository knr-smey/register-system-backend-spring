package register_system.backend.mapper;

import register_system.backend.dto.dtoRequest;
import register_system.backend.dto.dtoResponse;
import register_system.backend.model.classModels;

public class classMapper {
    public static dtoResponse toResponse(classModels classModels) {
        dtoResponse dtoRes = new dtoResponse();
        dtoRes.setId(classModels.getId());
        dtoRes.setCourse_id(classModels.getCourse_id());
        dtoRes.setTeacher_id(classModels.getTeacher_id());
        dtoRes.setStart_date(classModels.getStart_date());
        dtoRes.setMax_students(classModels.getMax_students());
        dtoRes.setStatus(classModels.getStatus());
        dtoRes.setCreated_at(classModels.getCreated_at() != null ? classModels.getCreated_at().toLocalDate() : null);
        dtoRes.setUpdated_at(classModels.getUpdated_at() != null ? classModels.getUpdated_at().toLocalDate() : null);
        return dtoRes;
    }
    public static classModels toEntity(dtoRequest dto){
        classModels classModel = new classModels();
        classModel.setCourse_id(dto.getCourse_id());
        classModel.setTeacher_id(dto.getTeacher_id());
        classModel.setStart_date(dto.getStart_date());
        classModel.setMax_students(dto.getMax_students());
        classModel.setStatus(dto.getStatus());
        return classModel;
    }
}

package register_system.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import register_system.backend.dto.dtoRequest;
import register_system.backend.dto.dtoResponse;
import register_system.backend.mapper.classMapper;
import register_system.backend.model.classModels;
import register_system.backend.repository.classRepository;
@Service
public class classService {
    private final classRepository classRepository;
    public classService(classRepository classRepository) {
        this.classRepository = classRepository;
    }
    public dtoResponse createClass(dtoRequest dto) throws IOException{
        classModels classModel = classMapper.toEntity(dto);
        classRepository.save(classModel);
        return classMapper.toResponse(classModel);
    }

     public List<dtoResponse> getAll(){

         List<classModels> classModelsList = new ArrayList<>();
        classModelsList = classRepository.findAll();
        return classModelsList.stream()
            .map(classMapper::toResponse)
            .toList();
    }
    public dtoResponse getById(Long id){
        classModels classModel = classRepository.findById(id).orElseThrow();
        return classMapper.toResponse(classModel);
    }
     public  dtoResponse delete(Long id ){
        classModels classModel = classRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Class not found"));
        

        classRepository.delete(classModel);
        return classMapper.toResponse(classModel);
    }
    public dtoResponse updateById(Long id, dtoRequest dto) throws IOException{
        classModels classModel = classRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Class not found"));
        classModel = classMapper.toEntity(dto);
        classRepository.save(classModel);
        return classMapper.toResponse(classModel);
    }
}

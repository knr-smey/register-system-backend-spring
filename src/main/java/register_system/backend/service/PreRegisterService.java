package register_system.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import register_system.backend.dto.PreRegisterRequestDTO;
import register_system.backend.dto.PreRegisterResponseDTO;
import register_system.backend.enums.RegistrationStatus;
import register_system.backend.mapper.PreRegisterMapper;
import register_system.backend.model.PreRegister;
import register_system.backend.repository.PreRegisterRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PreRegisterService {

    private final PreRegisterRepository repository;
    private final PreRegisterMapper mapper;
    private final String uploadDir = "uploads";

    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not save file", e);
        }
    }

    public List<PreRegisterResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PreRegisterResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Pre-registration not found with id: " + id));
    }

    @Transactional
    public PreRegisterResponseDTO create(PreRegisterRequestDTO dto) {
        PreRegister entity = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(entity));
    }

    @Transactional
    public PreRegisterResponseDTO update(Long id, PreRegisterRequestDTO dto) {
        PreRegister entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pre-registration not found with id: " + id));

        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setRequestDate(dto.getRequestDate());
        entity.setImageUrl(dto.getImageUrl());

        if (dto.getStatus() != null) {
            try {
                entity.setStatus(RegistrationStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Invalid status value: {}", dto.getStatus(), e);
            }
        }
        return mapper.toResponseDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pre-registration not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

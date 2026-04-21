package register_system.backend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import register_system.backend.dto.ScheduleDTO.ScheduleRequest;
import register_system.backend.dto.ScheduleDTO.ScheduleResponse;
import register_system.backend.model.Schedules;
import register_system.backend.repository.SchedulesRepository;
import register_system.backend.repository.classRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchedulesService {

    private final SchedulesRepository schedulesRepository;

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getAll(Long classId) {
        List<Schedules> schedules = (classId == null)
                ? schedulesRepository.findAll()
                : schedulesRepository.findByClassId(classId);

        return schedules.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponse getById(Long id) {
        return toResponse(findById(id));
    }

    @Transactional
    public ScheduleResponse create(ScheduleRequest request) {
        if (request.getStartTime() != null && request.getEndTime() != null) {
            validateTimes(request.getStartTime(), request.getEndTime());
        }

        Schedules schedules = new Schedules();
        schedules.setClassId(request.getClassId());
        schedules.setShift(normalizeShift(request.getShift()));
        schedules.setStartTime(request.getStartTime());
        schedules.setEndTime(request.getEndTime());

        return toResponse(schedulesRepository.save(schedules));
    }

    @Transactional
    public ScheduleResponse update(Long id, ScheduleRequest request) {
        Schedules schedules = findById(id);

        if (request.getStartTime() != null && request.getEndTime() != null) {
            validateTimes(request.getStartTime(), request.getEndTime());
        }

        schedules.setClassId(request.getClassId());
        schedules.setShift(normalizeShift(request.getShift()));
        schedules.setStartTime(request.getStartTime());
        schedules.setEndTime(request.getEndTime());

        return toResponse(schedulesRepository.save(schedules));
    }

    @Transactional
    public void delete(Long id) {
        Schedules schedules = findById(id);
        schedulesRepository.delete(schedules);
    }

    private Schedules findById(Long id) {
        return schedulesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + id));
    }

    private void validateTimes(LocalTime startTime, LocalTime endTime) {
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("end_time must be after start_time");
        }
    }

    private String normalizeShift(String shift) {
        String normalized = shift.trim().toLowerCase(Locale.ROOT);
        if (normalized.equals("day")) return "Day";
        if (normalized.equals("weekend")) return "Weekend";
        return shift.trim();
    }

    private ScheduleResponse toResponse(Schedules schedules) {
        return ScheduleResponse.builder()
                .id(schedules.getId())
                .classId(schedules.getClassId())
                .shift(schedules.getShift())
                .startTime(schedules.getStartTime())
                .endTime(schedules.getEndTime())
                .createdAt(schedules.getCreatedAt())
                .updatedAt(schedules.getUpdatedAt())
                .build();
    }
}


package register_system.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import register_system.backend.dto.ScheduleDTO.ScheduleRequest;
import register_system.backend.dto.ScheduleDTO.ScheduleResponse;
import register_system.backend.service.SchedulesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
@Tag(name = "Schedules", description = "API endpoints for managing schedules")
public class SchedulesController {

    private final SchedulesService schedulesService;

    @GetMapping
    @Operation(summary = "Get schedules", description = "Retrieve schedules, optionally filtered by class_id")
    public ResponseEntity<List<ScheduleResponse>> getAll(
            @RequestParam(value = "class_id", required = false) Long classId) {
        return ResponseEntity.ok(schedulesService.getAll(classId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get schedule by ID", description = "Retrieve a schedule by its ID")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(schedulesService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create schedule", description = "Create a new schedule")
    public ResponseEntity<ScheduleResponse> create(@Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulesService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update schedule", description = "Update an existing schedule by its ID")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(schedulesService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule", description = "Delete a schedule by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        schedulesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


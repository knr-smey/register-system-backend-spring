package register_system.backend.controller.course;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import register_system.backend.dto.course.CourseRequest;
import register_system.backend.dto.course.CourseResponse;
import register_system.backend.service.course.CourseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)

    @RequestBody(
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
    )

    public CourseResponse create(@Valid @ModelAttribute CourseRequest request) throws IOException {
        return courseService.create(request);
    }

    @GetMapping
    public List<CourseResponse> getAll(){
        return courseService.read();
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id){
        return courseService.getById(id);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestBody(
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
    )
    public CourseResponse update(
            @PathVariable Long id,
            @Valid @ModelAttribute CourseRequest request
    ) throws IOException {
        return courseService.update(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IOException{
        courseService.delete(id);
    }
}

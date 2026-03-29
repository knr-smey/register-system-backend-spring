package register_system.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import register_system.backend.dto.Test.TestRequest;
import register_system.backend.dto.Test.TestResponse;
import register_system.backend.service.TestService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/input")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public TestResponse create(@RequestBody TestRequest request) {
        return testService.create(request);
    }

    @GetMapping
    public List<TestResponse> getAll() {
        return testService.getAll();
    }
    
    @GetMapping("/{id}")
    public TestResponse getById(@PathVariable Long id) {
        return testService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testService.delete(id);
    }
}
package register_system.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import register_system.backend.dto.Test.TestRequest;
import register_system.backend.dto.Test.TestResponse;
import register_system.backend.service.TestService;

@RestController
@RequestMapping("/api/test")
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
package register_system.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import register_system.backend.dto.Test.TestRequest;
import register_system.backend.dto.Test.TestResponse;
import register_system.backend.mapper.TestMapper;
import register_system.backend.model.Test;
import register_system.backend.repository.TestRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepo testRepo;

    public TestResponse create(TestRequest request) {
        Test test = TestMapper.toEntity(request);
        Test saved = testRepo.save(test);
        return TestMapper.toResponse(saved);
    }

    public List<TestResponse> getAll() {
        return testRepo.findAll()
                .stream()
                .map(TestMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TestResponse getById(Long id) {
        Test test = testRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        return TestMapper.toResponse(test);
    }

    public void delete(Long id) {
        testRepo.deleteById(id);
    }
}
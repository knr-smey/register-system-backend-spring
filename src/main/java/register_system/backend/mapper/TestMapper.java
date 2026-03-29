package register_system.backend.mapper;

import register_system.backend.dto.Test.TestRequest;
import register_system.backend.dto.Test.TestResponse;
import register_system.backend.model.Test;

public class TestMapper {

    public static Test toEntity(TestRequest request) {
        return Test.builder()
                .test_name(request.getTestName())
                .test_password(request.getTestPassword())
                .build();
    }

    public static TestResponse toResponse(Test test) {
        return TestResponse.builder()
                .id(test.getId())
                .testName(test.getTest_name())
                .build();
    }
}
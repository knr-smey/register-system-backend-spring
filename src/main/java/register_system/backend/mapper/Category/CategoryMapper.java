package register_system.backend.mapper.Category;

import org.springframework.stereotype.Component;

import register_system.backend.dto.Category.CategoryRequest;
import register_system.backend.dto.Category.CategoryResponse;
import register_system.backend.model.category.CategoryModel;

import java.util.List;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryModel toEntity(CategoryRequest.Create request) {
        return CategoryModel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(request.getCreatedBy())
                .build();
    }

    public CategoryResponse.Detail toDetail(CategoryModel category) {
        return CategoryResponse.Detail.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdBy(category.getCreatedBy())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public CategoryResponse.Summary toSummary(CategoryModel category) {
        return CategoryResponse.Summary.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryResponse.Detail> toDetailList(List<CategoryModel> categories) {
        return categories.stream()
                .map(this::toDetail)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse.Summary> toSummaryList(List<CategoryModel> categories) {
        return categories.stream()
                .map(this::toSummary)
                .collect(Collectors.toList());
    }

    public void updateEntity(CategoryModel category, CategoryRequest.Update request) {
        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
    }
}
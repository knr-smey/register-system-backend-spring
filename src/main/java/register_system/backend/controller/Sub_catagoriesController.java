package register_system.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import register_system.backend.dto.sub_categories.request.Sub_catagories_query_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_request;
import register_system.backend.dto.sub_categories.request.Sub_catagories_update_request;
import register_system.backend.dto.sub_categories.response.Sub_catagories_page_response;
import register_system.backend.dto.sub_categories.response.Sub_catagories_response;
import register_system.backend.service.Sub_categories.Sub_CategoryService;   // FIXED: added the missing subpackage

import java.util.List;

@RestController
@RequestMapping("/api/sub-categories")
@Tag(name = "Sub-Category Management", description = "Endpoints for managing sub-categories")
public class Sub_catagoriesController {
    
    @Autowired
    private Sub_CategoryService subCategoryService;
    
    @PostMapping
    @Operation(summary = "Create a new sub-category", description = "Creates a new sub-category with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sub-category created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input or duplicate sub-category"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Sub_catagories_response> createSubCategory(
            @Valid @RequestBody Sub_catagories_request request) {
        Sub_catagories_response response = subCategoryService.createSubCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get sub-category by ID", description = "Retrieves a specific sub-category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sub-category found"),
        @ApiResponse(responseCode = "404", description = "Sub-category not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Sub_catagories_response> getSubCategoryById(
            @Parameter(description = "Sub-category ID", required = true)
            @PathVariable Long id) {
        Sub_catagories_response response = subCategoryService.getSubCategoryById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Get all sub-categories", description = "Retrieves a list of all sub-categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Sub_catagories_response>> getAllSubCategories() {
        List<Sub_catagories_response> responses = subCategoryService.getAllSubCategories();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update sub-category", description = "Updates an existing sub-category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sub-category updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input or duplicate sub-category"),
        @ApiResponse(responseCode = "404", description = "Sub-category not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Sub_catagories_response> updateSubCategory(
            @Parameter(description = "Sub-category ID", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Sub_catagories_update_request request) {
        Sub_catagories_response response = subCategoryService.updateSubCategory(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sub-category", description = "Deletes a specific sub-category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Sub-category deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Sub-category not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteSubCategory(
            @Parameter(description = "Sub-category ID", required = true)
            @PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/search")
    @Operation(summary = "Search sub-categories", description = "Searches sub-categories with filters and pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Sub_catagories_page_response> searchSubCategories(
            @RequestBody Sub_catagories_query_request queryRequest) {
        Sub_catagories_page_response response = subCategoryService.searchSubCategories(queryRequest);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/by-category/{categoryId}")
    @Operation(summary = "Get sub-categories by category ID", description = "Retrieves all sub-categories belonging to a specific category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Sub_catagories_response>> getSubCategoriesByCategoryId(
            @Parameter(description = "Parent Category ID", required = true)
            @PathVariable Long categoryId) {
        List<Sub_catagories_response> responses = subCategoryService.getSubCategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/exists")
    @Operation(summary = "Check if sub-category exists", description = "Checks if a sub-category exists with given name and category ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Check completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Boolean> existsByNameAndCategoryId(
            @RequestParam String name,
            @RequestParam Long categoryId) {
        boolean exists = subCategoryService.existsByNameAndCategoryId(name, categoryId);
        return ResponseEntity.ok(exists);
    }
}
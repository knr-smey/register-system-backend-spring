package register_system.backend.exception.subcatagory;

public class DuplicateSubCategoryException extends RuntimeException {
    
    public DuplicateSubCategoryException(String message) {
        super(message);
    }
    
    public DuplicateSubCategoryException(String name, Long categoryId) {
        super(String.format("Sub-category '%s' already exists under category ID: %d", name, categoryId));
    }
}
package register_system.backend.exception.subcatagory;

public class SubCategoryBusinessException extends RuntimeException {
    
    public SubCategoryBusinessException(String message) {
        super(message);
    }
    
    public SubCategoryBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
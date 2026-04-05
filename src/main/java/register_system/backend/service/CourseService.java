package register_system.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import register_system.backend.dto.CourseRequest;
import register_system.backend.dto.CourseResponse;
import register_system.backend.mapper.CourseMapper;
import register_system.backend.model.Course;
import register_system.backend.repository.CourseRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );
    // create
    public CourseResponse create(CourseRequest request) throws IOException {
        String imagePath = saveImage(request.getImage());

        Course course = CourseMapper.toEntity(request, imagePath);
        Course savedCourse = courseRepository.save(course);

        return CourseMapper.toResponse(savedCourse);
    }

    // read 
    public List<CourseResponse> read(){
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toResponse)
                .toList();
    }

    // get by id
    public CourseResponse getById(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: "+id));
        return CourseMapper.toResponse(course);
    }

    // update
    public CourseResponse update(Long id, CourseRequest request) throws IOException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: "+id));
        String imagePath = null;
        if(request.getImage() != null && !request.getImage().isEmpty()){
            deleteImageIfExists(existingCourse.getImage());
            imagePath = saveImage(request.getImage());
        }

        CourseMapper.updateEntity(existingCourse, request, imagePath);

        Course updatedCourse = courseRepository.save(existingCourse);
        return CourseMapper.toResponse(updatedCourse);
    }

    // delete
    public void delete(Long id) throws IOException{
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: "+id));
        deleteImageIfExists(course.getImage());
        courseRepository.delete(course);
    }

    // private function

    // delete image if image exists when update
    public void deleteImageIfExists(String imagePath) throws IOException {
        if(imagePath == null || imagePath.isBlank()) return;

        String fileName = Paths.get(imagePath).getFileName().toString();
        Path fullPath = Paths.get(uploadDir + "courses", fileName).toAbsolutePath().normalize();

        Files.deleteIfExists(fullPath);
    }

    // save image function
    private String saveImage(MultipartFile file) throws IOException {
        if(file == null || file.isEmpty()) return null;
        validateImage(file);

        String originalFileName = file.getOriginalFilename();

        if(originalFileName == null || originalFileName.isBlank()){
            throw new IOException("File name is missing");
        }

        originalFileName = StringUtils.cleanPath(originalFileName);

        if(originalFileName.contains("..")){
            throw new IOException("Invalid file name: " + originalFileName);
        }

        String extention = getFileExtension(originalFileName);
        String uniqueFileName = UUID.randomUUID() + extention;

        Path uploadPath = Paths.get(uploadDir, "courses").toAbsolutePath().normalize();

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path targetLocation = uploadPath.resolve(uniqueFileName).normalize();

        if(!targetLocation.startsWith(uploadPath)){
            throw new IOException("Invalid file path");
        }

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/courses/" + uniqueFileName;
    }

    // get file extention
    private String getFileExtension(String fileName){
        int lastDotIndex = fileName.lastIndexOf(".");
        if(lastDotIndex == -1) return "";

        return fileName.substring(lastDotIndex);
    }

    // validate image
    private void validateImage(MultipartFile file) {

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new RuntimeException("Invalid image file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Invalid image file");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new RuntimeException("Only JPG, PNG, and WEBP images are allowed");
        }
    }
}

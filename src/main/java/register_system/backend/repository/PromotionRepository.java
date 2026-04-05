package register_system.backend.repository;
import register_system.backend.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // រកប្រូម៉ូសិនដែល active (ថ្ងៃនេះស្ថិតក្នុងដំណើរការ)
    List<Promotion> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate startDate, LocalDate endDate);

    // រកតាម name
    List<Promotion> findByNameContainingIgnoreCase(String name);

    // ពិនិត្យ name មានហើយឬនៅ
    boolean existsByNameIgnoreCase(String name);
}
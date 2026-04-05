package register_system.backend.service;
import register_system.backend.dto.PromotionRequest;
import register_system.backend.dto.PromotionResponse;
import register_system.backend.model.Promotion;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import register_system.backend.repository.PromotionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    // ✅ Get All Promotions
    @Transactional(readOnly = true)
    public List<PromotionResponse> getAllPromotions() {
        return promotionRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Get Promotion By ID
    @Transactional(readOnly = true)
    public PromotionResponse getPromotionById(Long id) {
        Promotion promotion = findById(id);
        return toResponse(promotion);
    }

    // ✅ Get Active Promotions (ថ្ងៃនេះ)
    @Transactional(readOnly = true)
    public List<PromotionResponse> getActivePromotions() {
        LocalDate today = LocalDate.now();
        return promotionRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Create Promotion
    @Transactional
    public PromotionResponse createPromotion(PromotionRequest request) {
        validateDates(request.getStartDate(), request.getEndDate());

        Promotion promotion = Promotion.builder()
                .name(request.getName())
                .discountPercent(request.getDiscountPercent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        return toResponse(promotionRepository.save(promotion));
    }

    // ✅ Update Promotion
    @Transactional
    public PromotionResponse updatePromotion(Long id, PromotionRequest request) {
        Promotion promotion = findById(id);
        validateDates(request.getStartDate(), request.getEndDate());

        promotion.setName(request.getName());
        promotion.setDiscountPercent(request.getDiscountPercent());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());

        return toResponse(promotionRepository.save(promotion));
    }

    // ✅ Delete Promotion
    @Transactional
    public void deletePromotion(Long id) {
        Promotion promotion = findById(id);
        promotionRepository.delete(promotion);
    }

    // ── Private Helpers ──────────────────────────────────────────────────────

    private Promotion findById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Promotion not found with id: " + id));
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }

    private PromotionResponse toResponse(Promotion promotion) {
        return PromotionResponse.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .discountPercent(promotion.getDiscountPercent())
                .startDate(promotion.getStartDate())
                .endDate(promotion.getEndDate())
                .createdAt(promotion.getCreatedAt())
                .updatedAt(promotion.getUpdatedAt())
                .build();
    }
}
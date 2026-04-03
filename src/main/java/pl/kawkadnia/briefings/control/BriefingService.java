package pl.kawkadnia.briefings.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kawkadnia.briefings.entity.Briefing;
import pl.kawkadnia.briefings.entity.BriefingEntity;
import pl.kawkadnia.briefings.entity.CreateBriefingRequest;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class BriefingService {

    private final ObjectMapper objectMapper;

    public Briefing getBriefingByDate(LocalDate date) {
        BriefingEntity entity = BriefingEntity.findByDate(date);
        if (entity == null) {
            throw new NotFoundException("No briefing found for date: " + date);
        }
        return toDto(entity);
    }

    public List<LocalDate> getAllDates() {
        return BriefingEntity.find("ORDER BY date DESC")
                .stream()
                .map(e -> ((BriefingEntity) e).getDate())
                .toList();
    }

    @Transactional
    public Briefing createBriefing(CreateBriefingRequest request) {
        BriefingEntity existing = BriefingEntity.findByDate(request.getDate());
        if (existing != null) {
            throw new IllegalStateException("Briefing already exists for date: " + request.getDate());
        }

        BriefingEntity entity = new BriefingEntity();
        entity.setDate(request.getDate());
        entity.setContent(request.getContent().toString());
        entity.setArticlesFetched(request.getArticlesFetched());
        entity.setSourcesCount(request.getSourcesCount());
        entity.persist();

        log.info("Created briefing for date: {}", request.getDate());
        return toDto(entity);
    }

    private Briefing toDto(BriefingEntity entity) {
        try {
            return new Briefing(
                    entity.getId(),
                    entity.getDate(),
                    entity.getCreatedAt(),
                    objectMapper.readTree(entity.getContent()),
                    entity.getArticlesFetched(),
                    entity.getSourcesCount()
            );
        } catch (JsonProcessingException e) {
            log.error("Failed to parse briefing content JSON for id: {}", entity.getId(), e);
            throw new RuntimeException("Failed to parse briefing content", e);
        }
    }
}

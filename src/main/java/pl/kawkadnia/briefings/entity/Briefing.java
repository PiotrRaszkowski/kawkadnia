package pl.kawkadnia.briefings.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Briefing {
    private String id;
    private LocalDate date;
    private LocalDateTime createdAt;
    private JsonNode content;
    private Integer articlesFetched;
    private Integer sourcesCount;
}

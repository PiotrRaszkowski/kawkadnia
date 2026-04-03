package pl.kawkadnia.briefings.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBriefingRequest {

    @NotNull
    private LocalDate date;

    @NotNull
    private JsonNode content;

    private Integer articlesFetched;

    private Integer sourcesCount;
}

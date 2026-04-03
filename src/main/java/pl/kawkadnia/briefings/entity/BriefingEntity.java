package pl.kawkadnia.briefings.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "briefings")
@Getter
@Setter
public class BriefingEntity extends PanacheEntityBase {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private LocalDate date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "JSON")
    private String content;

    @Column
    private Integer articlesFetched;

    @Column
    private Integer sourcesCount;

    @PrePersist
    public void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public static BriefingEntity findByDate(LocalDate date) {
        return find("date", date).firstResult();
    }

    public static List<LocalDate> findAllDates() {
        return find("ORDER BY date DESC")
                .project(LocalDate.class)
                .list();
    }
}

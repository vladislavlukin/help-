package main.modul;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.tuple.GenerationTiming;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CurrentTimestamp(timing = GenerationTiming.ALWAYS)
    private LocalDateTime creationTime;
    private boolean isDone;
    private String title;
    private String description;
}

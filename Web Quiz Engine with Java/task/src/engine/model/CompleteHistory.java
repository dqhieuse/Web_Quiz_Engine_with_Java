package engine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CompleteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long h_id;

    @ManyToOne
    Quiz quiz;

    @ManyToOne
    @JsonIgnore
    Author author;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    LocalDateTime completedAt = LocalDateTime.now();

    public CompleteHistory(Quiz quiz) {
        this.quiz = quiz;
    }
}

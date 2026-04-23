package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    String title;

    String text;

    @ElementCollection(fetch = FetchType.EAGER)
    List<String> options;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    List<Integer> answer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_email")
    Author author;

}

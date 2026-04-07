package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz {
    @JsonIgnore
    String id;
    String title;
    String text;
    List<String> options;
    @JsonIgnore
    int correctAns;
}

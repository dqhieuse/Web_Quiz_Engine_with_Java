package engine.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateQuizRequest {
    @NotBlank
    String title;

    @NotBlank
    String text;

    @Size(min = 2)
    List<String> options;

    @NotNull
    int answer;
}

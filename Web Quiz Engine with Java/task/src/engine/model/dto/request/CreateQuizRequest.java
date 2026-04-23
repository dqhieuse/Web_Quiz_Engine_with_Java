package engine.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotNull
    @NotEmpty
    String title;

    @NotNull
    @NotEmpty
    String text;

    @Size(min = 2)
    @NotNull
    List<String> options;

    List<Integer> answer;
}

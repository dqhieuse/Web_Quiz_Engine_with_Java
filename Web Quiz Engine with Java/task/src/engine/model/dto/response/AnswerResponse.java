package engine.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerResponse {

    Boolean success;
    String feedback;

    public static AnswerResponse correct() {
        return new AnswerResponse(true, "Congratulations, you're right!");
    }

    public static AnswerResponse wrong() {
        return new AnswerResponse(false, "Wrong answer! Please, try again.");
    }
}

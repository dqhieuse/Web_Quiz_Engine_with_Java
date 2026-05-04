package engine.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CompletedTaskResponse(
        Long id, 
        LocalDateTime completedAt) {
}

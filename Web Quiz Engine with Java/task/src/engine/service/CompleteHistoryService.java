package engine.service;

import engine.model.dto.response.CompletedTaskResponse;
import engine.repository.CompleteHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CompleteHistoryService {
    private final CompleteHistoryRepository completeHistoryRepository;

    public CompleteHistoryService(CompleteHistoryRepository completeHistoryRepository) {
        this.completeHistoryRepository = completeHistoryRepository;
    }

    public Page<CompletedTaskResponse> getAllCompleteHistoryOfCurrentUser(UserDetails currentUser, Pageable pageable) {
        return completeHistoryRepository.getCompleteHistoriesByAuthor_Email(currentUser.getUsername(), pageable).map(history ->
                new CompletedTaskResponse(history.getQuiz().getId(), history.getCompletedAt())
        );
    }
}

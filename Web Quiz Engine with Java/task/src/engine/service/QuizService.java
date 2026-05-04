package engine.service;

import engine.exception.NotFoundException;
import engine.model.Author;
import engine.model.CompleteHistory;
import engine.model.Quiz;
import engine.model.dto.request.CheckQuizRequest;
import engine.model.dto.request.CreateQuizRequest;
import engine.model.dto.response.AnswerResponse;
import engine.repository.CompleteHistoryRepository;
import engine.repository.QuizRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CompleteHistoryRepository completeHistoryRepository;

    public QuizService(QuizRepository quizRepository, CompleteHistoryRepository completeHistoryRepository) {
        this.quizRepository = quizRepository;
        this.completeHistoryRepository = completeHistoryRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz createQuiz(CreateQuizRequest request, UserDetails user) {
        Quiz quiz = new Quiz();
        BeanUtils.copyProperties(request, quiz);

        Author author = Author.builder()
                .email(user.getUsername())
                .password(user.getPassword())
                .build();

        quiz.setAuthor(author);

        if (null == quiz.getOptions()) {
            quiz.setOptions(new ArrayList<>());
        }

        return quizRepository.save(quiz);
    }

    public Quiz getQuiz(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quiz with id " + id + " not found"));
    }

    public AnswerResponse checkAnswer(Long id, CheckQuizRequest request, UserDetails user) {
        Quiz quiz = getQuiz(id);

        boolean isCorrect = false;

        if (quiz.getAnswer() == null || quiz.getAnswer().isEmpty()) {
             isCorrect = request.getAnswer() == null || request.getAnswer().isEmpty();
        } else if (request.getAnswer() != null && quiz.getAnswer().size() == request.getAnswer().size()) {
            isCorrect = quiz.getAnswer().containsAll(request.getAnswer());
        }

        if (isCorrect) {
            CompleteHistory history = new CompleteHistory();
            history.setQuiz(quiz);
            history.setAuthor(Author.builder().email(user.getUsername()).build());
            history.setCompletedAt(LocalDateTime.now());
            completeHistoryRepository.save(history);
            return AnswerResponse.correct();
        }

        return AnswerResponse.wrong();
    }

    public void deleteQuiz(Long id, UserDetails user) {
        Quiz quiz = getQuiz(id);

        if (!quiz.getAuthor().getEmail().equals(user.getUsername())) {
            throw new AccessDeniedException("You are not the author of this quiz");
        }

        quizRepository.delete(quiz);
    }

    public Page<Quiz> getAllQuizzes(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }
}

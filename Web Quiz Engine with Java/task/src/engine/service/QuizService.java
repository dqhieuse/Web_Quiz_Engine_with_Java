package engine.service;

import engine.WebQuizEngine;
import engine.exception.IndexOutOfBoundsException;
import engine.exception.NotFoundException;
import engine.model.Author;
import engine.model.Quiz;
import engine.model.dto.request.CheckQuizRequest;
import engine.model.dto.request.CreateQuizRequest;
import engine.model.dto.response.AnswerResponse;
import engine.repository.QuizRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
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

    public AnswerResponse checkAnswer(Long id, CheckQuizRequest request) {
        Quiz quiz = getQuiz(id);

        if (quiz.getAnswer().isEmpty() && request.getAnswer().isEmpty()) {
            return AnswerResponse.correct();
        }

        if (quiz.getAnswer().size() != request.getAnswer().size()) {
            return AnswerResponse.wrong();
        }

        for (Integer answer : request.getAnswer()) {
            if (!quiz.getAnswer().contains(answer)) {
                return AnswerResponse.wrong();
            }
        }
        return AnswerResponse.correct();
    }

    public void deleteQuiz(Long id, UserDetails user) {
        Quiz quiz = getQuiz(id);

        if (!quiz.getAuthor().getEmail().equals(user.getUsername())) {
            throw new AccessDeniedException("You are not the author of this quiz");
        }

        quizRepository.delete(quiz);
    }
}

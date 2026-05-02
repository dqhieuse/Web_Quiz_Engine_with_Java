package engine.controller;

import engine.model.Quiz;
import engine.model.dto.request.CheckQuizRequest;
import engine.model.dto.request.CreateQuizRequest;
import engine.model.dto.response.AnswerResponse;
import engine.model.dto.response.CompletedTaskResponse;
import engine.service.CompleteHistoryService;
import engine.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
class QuizController {

    private final QuizService quizService;
    private final CompleteHistoryService completeHistoryService;

    QuizController(QuizService quizService, CompleteHistoryService completeHistoryService) {
        this.quizService = quizService;
        this.completeHistoryService = completeHistoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerResponse> checkAnswer(@PathVariable Long id, @RequestBody @Valid CheckQuizRequest request) {
        return new ResponseEntity<>(quizService.checkAnswer(id, request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody CreateQuizRequest request, @AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(quizService.createQuiz(request, user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Quiz>> getAllQuizzes(@Param("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return new ResponseEntity<>(quizService.getAllQuizzes(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        quizService.deleteQuiz(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<CompletedTaskResponse>> getCompletedQuizzes(@Param("page") Integer page, @AuthenticationPrincipal UserDetails user) {
        Pageable pageable = PageRequest.of(page, 10);
        return new ResponseEntity<>(completeHistoryService.getAllCompleteHistoryOfCurrentUser(user, pageable), HttpStatus.OK);
    }
}

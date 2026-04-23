package engine.controller;

import engine.model.Quiz;
import engine.model.dto.request.CheckQuizRequest;
import engine.model.dto.request.CreateQuizRequest;
import engine.model.dto.response.AnswerResponse;
import engine.service.QuizService;
import jakarta.validation.Valid;
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

    QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);
    }

    @PostMapping("{id}/solve")
    public ResponseEntity<AnswerResponse> checkAnswer(@PathVariable Long id, @RequestBody @Valid CheckQuizRequest request) {
        return new ResponseEntity<>(quizService.checkAnswer(id, request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody CreateQuizRequest request, @AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(quizService.createQuiz(request, user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        quizService.deleteQuiz(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

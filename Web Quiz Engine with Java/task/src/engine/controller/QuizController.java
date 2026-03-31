package engine.controller;

import engine.WebQuizEngine;
import engine.model.Quiz;
import engine.model.dto.request.CheckQuizRequest;
import engine.model.dto.request.CreateQuizRequest;
import engine.model.dto.response.AnswerResponse;
import engine.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
class QuizController {

    private final QuizService quizService;

    QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable int id) {
        return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);
    }

    @PostMapping("{id}/solve")
    public ResponseEntity<AnswerResponse> checkAnswer(@PathVariable Integer id, @RequestBody @Valid CheckQuizRequest request) {
        return new ResponseEntity<>(quizService.checkAnswer(id, request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody CreateQuizRequest request) {
        return new ResponseEntity<>(quizService.createQuiz(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(WebQuizEngine.quizzes, HttpStatus.OK);
    }
}

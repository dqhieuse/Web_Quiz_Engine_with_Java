package engine.controller;

import engine.WebQuizEngine;
import engine.model.Quiz;
import engine.model.dto.response.AnswerResponse;
import engine.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
class QuizController {

    private final QuizService quizService;

    QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<Quiz> getQuiz() {
        return new ResponseEntity<>(WebQuizEngine.quiz, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnswerResponse> checkAnswer(@RequestParam("answer") int answer) {
        return quizService.checkAnswer(answer);
    }
}

package engine.service;

import engine.WebQuizEngine;
import engine.exception.IndexOutOfBoundsException;
import engine.model.dto.response.AnswerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    public ResponseEntity<AnswerResponse> checkAnswer(int answer) {
        if (answer < 1 || answer > WebQuizEngine.quiz.getOptions().size()) {
            throw new IndexOutOfBoundsException("Answer should be in range 1 to " + WebQuizEngine.quiz.getOptions().size());
        }

        if (WebQuizEngine.quiz.getCorrectAns() == answer - 1) {
            return new ResponseEntity<>(AnswerResponse.correct(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(AnswerResponse.wrong(), HttpStatus.OK);
        }
    }
}

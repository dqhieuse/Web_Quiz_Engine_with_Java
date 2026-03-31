package engine.service;

import engine.WebQuizEngine;
import engine.exception.NotFoundException;
import engine.model.Quiz;
import engine.model.dto.request.CheckQuizRequest;
import engine.model.dto.request.CreateQuizRequest;
import engine.model.dto.response.AnswerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    public Quiz getQuiz(int id) {
        return WebQuizEngine.quizzes.stream().filter(q -> q.getId() == id).findFirst().orElseThrow(() -> new NotFoundException("Quiz with id " + id + " not found"));
    }

    public AnswerResponse checkAnswer(int id, CheckQuizRequest request) {
        Quiz quiz = getQuiz(id);

//        if (answer < 1 || answer > quiz.getOptions().size()) {
//            throw new IndexOutOfBoundsException("Answer should be in range 1 to " + quiz.getOptions().size());
//        }

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

    public Quiz createQuiz(CreateQuizRequest request) {
        Quiz quiz = new Quiz();
        BeanUtils.copyProperties(request, quiz);
        if (null == quiz.getAnswer()) {
            quiz.setAnswer(new ArrayList<>());
        }
//        if (quiz.getAnswer() < 1 || quiz.getAnswer() > quiz.getOptions().size()) {
//            throw new IndexOutOfBoundsException("Answer should be in range 1 to " + quiz.getOptions().size());
//        }
        quiz.setId(WebQuizEngine.quizzes.size() + 1);
        WebQuizEngine.quizzes.add(quiz);
        return quiz;
    }
}

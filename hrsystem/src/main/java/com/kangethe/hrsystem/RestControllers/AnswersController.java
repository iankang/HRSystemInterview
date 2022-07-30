package com.kangethe.hrsystem.RestControllers;

import com.kangethe.hrsystem.entities.Answers;
import com.kangethe.hrsystem.services.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/answers")
@Tag(name = "Answers", description = "The answers to the policy questions ")
public class AnswersController {

    private final AnswerService answerService;

    public AnswersController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(summary = "Create An Answer", description = "creates an answer to a policy question")
    @PostMapping("/{questionId}")
    public ResponseEntity<Answers> createAnswer(
            @Parameter(description = "the question ID to which you want to add a question", required = true)
            @PathVariable("questionId") Long questionId,
            @Parameter(description = "the actual answer", required = true)
            @RequestParam("answer") String answer,
            @Parameter(description = "Is this the actual Correct Answer", required = true)
            @RequestParam("isCorrect") Boolean isCorrect
    ) {
        Answers anAnswer = new Answers(answer, isCorrect);
        return answerService.createAnswer(questionId, anAnswer);
    }


    @Operation(summary = "Get All Answers", description = "Gets all answers")
    @GetMapping("/{questionId}")
    public ResponseEntity<List<Answers>> getAnswersFromQuestionId(
            @Parameter(description = "Question Id from which you want all answers", required = true)
            @PathVariable("questionId") Long questionId) {

        return answerService.getAllAnswers(questionId);
    }

    @Operation(summary = "Get Single Answer", description = "Gets Single Answer By Id")
    @GetMapping("/getAnswer/{answerId}")
    public ResponseEntity<Answers> getAnswerById(
            @PathVariable("answerId") Long answerId
    ) {
        return answerService.getAnswerById(answerId);
    }

    @Operation(summary = "update answer", description = "Updates an answer")
    @PutMapping("/update/{answerId}")
    public ResponseEntity<Answers> updateAnswer(
            @PathVariable("answerId") Long answerId,
            @Parameter(description = "the actual answer", required = true)
            @RequestParam("answer") String answer,
            @Parameter(description = "Is this the actual Correct Answer", required = true)
            @RequestParam("isCorrect") Boolean isCorrect
    ) {
        Answers anAnswer = new Answers(answer, isCorrect);
        return answerService.updateAnswer(answerId, anAnswer);
    }

    @Operation(summary = "delete answer", description = "Deletes an answer")
    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<HttpStatus> deleteAnswer(
            @PathVariable("answerId") Long answerId
    ) {
        return answerService.deleteAnswer(answerId);
    }
}

package com.kangethe.hrsystem.RestControllers;


import com.kangethe.hrsystem.entities.TopicQuestions;
import com.kangethe.hrsystem.repositories.TopicsQuestionRepository;
import com.kangethe.hrsystem.services.TopicQuestionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.glassfish.hk2.api.messaging.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questions")
@Tag(name = "Questions", description = "The Questions available under a topic")
@PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('MODERATOR')")
public class TopicQuestionsController {

    private final TopicQuestionsService topicsQuestionService;

    public TopicQuestionsController(TopicQuestionsService topicsQuestionService) {
        this.topicsQuestionService = topicsQuestionService;
    }

    @Operation(summary = "Add a question under a policy topic",description = "Adds a question to topic",tags = {"Questions"})
    @PostMapping("/topicId/{topicId}/topicQuestion")
    public ResponseEntity<TopicQuestions> createQuestion(
            @Parameter(description = "topic Id to add question to", required = true)
            @PathVariable("topicId") Long topicId,

            @Parameter(description = "the body of the actual question", schema = @Schema(implementation = TopicQuestions.class))
            @RequestBody TopicQuestions topicQuestions
    ){
        return topicsQuestionService.createTopicQuestion(topicId,topicQuestions);
    }

    @Operation(summary = "Get All questions from a topic", description = "Gets all questions belonging to a particular topic",tags = {"Questions"})
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<TopicQuestions>> getAllQuestions(
            @Parameter(description = "topic id that you want questions from", required = true)
            @PathVariable("topicId") Long topicId
    ){
        return topicsQuestionService.getAllQuestionsByTopicId(topicId);
    }

    @Operation(summary = "Get Question By Id", description = "Gets Question By Id",tags = {"Questions"})
    @GetMapping("/{questionId}")
    public ResponseEntity<TopicQuestions> getQuestionById(
            @Parameter(description = "id of question you want to fetch", required = true)
           @PathVariable("questionId") Long questionId
    ){
        return topicsQuestionService.getTopicQuestionByQuestionId(questionId);
    }

    @Operation(summary = "Update A Question", description = "Updates a Question",tags = {"Questions"})
    @PutMapping("/{question}")
    public ResponseEntity<TopicQuestions> updateQuestion(
            @Parameter(description = "id of question you want to update", required = true)
            @PathVariable("question") Long questionId,
            @Parameter(description = "new question body", required = true, schema = @Schema(implementation = TopicQuestions.class))
            @RequestBody TopicQuestions updatedQuestion){
        return topicsQuestionService.updateQuestion(questionId, updatedQuestion);
    }

    @Operation(summary = "delete A Question", description = "Deletes A question", tags = {"Questions"})
    @DeleteMapping("/{questionId}")
    public ResponseEntity<HttpStatus> deleteQuestion(
            @Parameter(description = "id of the question you want deleted", required = true)
            @PathVariable("questionId") Long questionId){
        return topicsQuestionService.deleteQuestion(questionId);
    }
}

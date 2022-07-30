package com.kangethe.hrsystem.RestControllers;

import com.kangethe.hrsystem.entities.Topics;
import com.kangethe.hrsystem.services.TopicsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topics")
@Tag(name = "Topics", description = "The Policy Topics API")
public class TopicsController {

    private final TopicsService topicsService;

    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @Operation(summary = "Add a new HR topic", description = "Adds a HR topic.", tags = {"Topics"})
    @PostMapping("/createTopic")
    public ResponseEntity<Topics> createTopic(
            @Parameter(
                    description = "Topic to add.",
                    required = true,
                    schema = @Schema(implementation = Topics.class)
            )
            @RequestBody Topics topics
    ) {
        return topicsService.createTopic(topics);
    }

    @Operation(summary = "Add a new HR topic using a form input", description = "Adds a HR topic.", tags = {"Topics"})
    @PostMapping("/createTopicForm")
    public ResponseEntity<Topics> createTopicForm(
            @Parameter(description = "name of topic")
           @RequestParam("topic") String topicName
    ) {

        Topics topic  = new Topics(topicName);
        return topicsService.createTopic(topic);
    }

    @Operation(summary = "Get all Topics", description = "Gets All topics", tags = {"Topics"})
    @GetMapping("/getAllTopics")
    public ResponseEntity<List<Topics>> getAllTopics() {
        return topicsService.getTopics();
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<Topics> updateTopic(
            @PathParam("topicId") Long topicId,
            @RequestBody Topics topic
    ) {
        return topicsService.updateTopic(topic, topicId);
    }

    @DeleteMapping("/delete/{topicId}")
    public ResponseEntity removeTopic(
            @PathParam("topicId") Long topicId) {
        return topicsService.deleteTopic(topicId);
    }
}

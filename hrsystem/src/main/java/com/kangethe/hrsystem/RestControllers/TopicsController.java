package com.kangethe.hrsystem.RestControllers;

import com.kangethe.hrsystem.entities.Topics;
import com.kangethe.hrsystem.services.TopicsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topics")
public class TopicsController {

    private final TopicsService topicsService;

    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @PostMapping("/createTopic")
    public ResponseEntity<Topics> createTopic(
            @RequestBody Topics topics
    ) {
        return topicsService.createTopic(topics);
    }

    @PostMapping("/createTopicForm")
    public ResponseEntity<Topics> createTopicForm(
           @RequestParam("topic") String topicName
    ) {

        Topics topic  = new Topics(topicName);
        return topicsService.createTopic(topic);
    }

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

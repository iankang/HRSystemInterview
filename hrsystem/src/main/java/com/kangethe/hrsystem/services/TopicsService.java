package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Topics;
import com.kangethe.hrsystem.repositories.TopicsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TopicsService {

    private TopicsRepository topicsRepository;

    public TopicsService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public ResponseEntity<Topics> createTopic(Topics topic) {
        if(topic != null) {
            return new ResponseEntity<>(topicsRepository.save(topic), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Topics>> getTopics() {

        List<Topics> allTopics = topicsRepository.findAll();
        return  ResponseEntity.ok(allTopics);
    }

    public ResponseEntity<Topics> getTopic(Long id) {
        Optional<Topics> topicOptional = topicsRepository.findById(id);
        if( topicOptional.isPresent()){
            return ResponseEntity.ok(topicOptional.get());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Topics> updateTopic(Topics topics, Long topicId) {
        Optional<Topics> topicsOptional = topicsRepository.findById(topicId);
        if(topicsOptional.isPresent()){
            Topics topicEdit = topicsOptional.get();
            topicEdit.setTopicName(topics.getTopicName());
            return ResponseEntity.ok(topicsRepository.save(topicEdit));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity deleteTopic(Long topicId) {
        Optional<Topics> topicsOptional = topicsRepository.findById(topicId);
        if(topicsOptional.isPresent()){
            topicsRepository.deleteById(topicId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

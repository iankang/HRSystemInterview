package com.kangethe.hrsystem.RestControllers;

import com.kangethe.hrsystem.entities.AssessmentScore;
import com.kangethe.hrsystem.entities.Scores;
import com.kangethe.hrsystem.services.AssessmentScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/scores")
@Tag(name = "Scores", description = "The Scores reference for the assessments")
public class AssessmentScoresController {

    private final AssessmentScoreService assessmentScoreService;

    public AssessmentScoresController(AssessmentScoreService assessmentScoreService) {
        this.assessmentScoreService = assessmentScoreService;
    }

    @Operation(summary = "Add a score for assessment", description = "Adds a score ", tags = {"Scores"})
    @PostMapping
    public ResponseEntity<AssessmentScore> createAssessment(@Parameter(description = "the narration for the score") @RequestParam Scores assessmentScore, @Parameter(description = "the lower bound for the score") @RequestParam Long scoreLowerBound, @Parameter(description = "the upper bound for the score") @RequestParam Long scoreUpperBound) {
        AssessmentScore assessmentScoreElement = new AssessmentScore(assessmentScore, scoreLowerBound, scoreUpperBound);
        return assessmentScoreService.addAssessment(assessmentScoreElement);
    }

    @Operation(summary = "Get scores for assessment", description = "Adds a score ", tags = {"Scores"})
    @GetMapping
    public ResponseEntity<List<AssessmentScore>> getAllAssessments() {
        return assessmentScoreService.getAllAssessments();
    }

    @Operation(summary = "Update scores for assessment", description = "Updates a score", tags = {"Scores"})
    @PutMapping("/{assessmentScoreId}")
    public ResponseEntity<AssessmentScore> updateAssessment(@Parameter(description = "path variable for the score to update") @PathVariable("assessmentScoreId") Long assessmentScoreId, @RequestParam Scores assessmentScore, @Parameter(description = "the lower bound for the score") @RequestParam Long scoreLowerBound, @Parameter(description = "the upper bound for the score") @RequestParam Long scoreUpperBound) {
        AssessmentScore updatedScore = new AssessmentScore(assessmentScore, scoreLowerBound, scoreUpperBound);
        return assessmentScoreService.updateAssessment(assessmentScoreId, updatedScore);
    }

    @Operation(summary = "Delete scores for assessment", description = "Deletes a score", tags = {"Scores"})
    @DeleteMapping("/{assessmentScoreId}")
    public ResponseEntity<HttpStatus> deleteAssessment(@Parameter(description = "assessment id which you want deleted") @PathVariable("assessmentScoreId") Long assessmentId) {
        return assessmentScoreService.deleteAssessment(assessmentId);
    }

    @Operation(summary = "Delete All the scores for assessment", description = "Deletes all scores", tags = {"Scores"})
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllAssessment() {
        return assessmentScoreService.deleteAllAssessments();
    }
}

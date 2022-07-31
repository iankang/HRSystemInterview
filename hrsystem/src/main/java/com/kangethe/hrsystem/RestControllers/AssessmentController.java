package com.kangethe.hrsystem.RestControllers;

import com.kangethe.hrsystem.entities.Assessment;
import com.kangethe.hrsystem.services.AssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/assessments")
@Tag(name = "Assessments", description = "The Assessments")
public class AssessmentController {

    private AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }
    @Operation(summary = "Get all assessments in the system", description = "Gets all assessments ", tags = {"Assessments"})
    @GetMapping("/getAll")
    public ResponseEntity<List<Assessment>> getAllAssessments(){
        return assessmentService.getAllAssessments();
    }

    @Operation(summary = "Get all assessments done by user", description = "Gets all assessments by specific user ", tags = {"Assessments"})
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Assessment>> getAssessmentsByUserId(
            @Parameter(description = "userId you want to get assessments for")
            @PathVariable("userId") Long userId){
        return assessmentService.getAssessmentsByUserId(userId);
    }

    @Operation(summary = "Add assessment", description = "Adds an assessment", tags = {"Assessments"})
    @PostMapping
    public ResponseEntity<Assessment> createAssessment(
            @Parameter(description = "userId you want to add assessments for")
            @RequestParam("userId") Long userId
    ){
        return assessmentService.createAssessment(userId);
    }

    @Operation(summary = "Remove assessment", description = "Removes an assessment", tags = {"Assessments"})
    @DeleteMapping("/{assessmentId}")
    public ResponseEntity<HttpStatus> deleteAssessmentById(
            @Parameter(description = "Assessment Id you want to remove")
            @PathVariable("assessmentId") Long assessmentId){
        return assessmentService.deleteAssessment(assessmentId);
    }
}

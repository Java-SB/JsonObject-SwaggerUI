package com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.controller;

import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.dto.ScoreRequestDto;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.model.Score;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.service.ScoreService;
import com.ecodriver.platform.u202023992.si729ebu202023992.common.exception.ValidationException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Score", description = "Controlled by Score")
@RestController
@RequestMapping("/api/v1")
public class ScoreController {
    private final ScoreService scoreService;
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/drivers/{driverId}/scores")
    public ResponseEntity<Object> getScoreById(@PathVariable Long driverId, @RequestParam(required = false) String scope) {
        if (scope == null || scope.trim().isEmpty()) {
            throw new ValidationException("Scope value not specified");
        }
        else {
            if (scope.equals("0")) {
                return ResponseEntity.ok(scoreService.getAverageScore(driverId));
            } else if (scope.equals("1")) {
                return ResponseEntity.ok(scoreService.getMaxScore(driverId));
            } else {
                throw new ValidationException("Scope value not valid");
            }
        }
    }

    @Transactional
    @PostMapping("/drivers/{driverId}/scores")
    public ResponseEntity<Score> createScore(@RequestBody ScoreRequestDto scoreRequestDto) {
        return new ResponseEntity<>(scoreService.createScore(scoreRequestDto), HttpStatus.CREATED);
    }
}

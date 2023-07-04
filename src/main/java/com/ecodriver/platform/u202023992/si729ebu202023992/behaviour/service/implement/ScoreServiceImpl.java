package com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.service.implement;

import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.dto.ScoreRequestDto;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.model.Score;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.repository.ScoreRepository;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.service.ScoreService;
import com.ecodriver.platform.u202023992.si729ebu202023992.common.exception.ResourceNotFoundException;
import com.ecodriver.platform.u202023992.si729ebu202023992.common.exception.ValidationException;
import com.ecodriver.platform.u202023992.si729ebu202023992.common.mapper.MapperMethods;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ScoreServiceImpl implements ScoreService {
    private final MapperMethods mapperMethods;
    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(MapperMethods mapperMethods, ScoreRepository scoreRepository) {
        this.mapperMethods = mapperMethods;
        this.scoreRepository = scoreRepository;
    }
    @Override
    public Score createScore(ScoreRequestDto scoreRequestDto) {
        scoreRequestDto.setRegisteredAt(new Date());
        Score score = mapperMethods.toScore(scoreRequestDto);
        validateScore(score);
        return scoreRepository.save(score);
    }
    @Override
    public Map<String, Object> getAverageScore(Long driverId) {
        OptionalDouble avgScoreValue = scoreRepository.findByDriverId(driverId).stream().mapToDouble(Score::getValue).average();
        if (avgScoreValue.isPresent()) {
            Map<String, Object> score = new HashMap<>();
            score.put("averageScore", avgScoreValue.getAsDouble());
            return objectJson(score, driverId);
        } else {
            throw new ResourceNotFoundException("Score not found");
        }
    }
    @Override
    public Map<String, Object> getMaxScore(Long driverId) {
        Optional<Float> maxScoreValue = scoreRepository.findByDriverId(driverId).stream().map(Score::getValue).max(Comparator.naturalOrder());
        if (maxScoreValue.isPresent()) {
            Map<String, Object> score = new HashMap<>();
            score.put("maxScore", maxScoreValue.get());
            return objectJson(score, driverId);
        } else {
            throw new ResourceNotFoundException("Score not found");
        }
    }
    private Map<String, Object> objectJson(Map<String, Object> score, Long driverId) {
        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("driverId", driverId);
        return response;
    }
    private void validateScore(Score score){
        if(score.getDriverId() == null || score.getDriverId() <= 0){
            throw new ValidationException("Driver id cannot be empty");
        }
        if(score.getValue() <= 0.0f){
            throw new ValidationException("Score value cannot be empty");
        }
        if(score.getRegisteredAt() == null){
            throw new ValidationException("Registered at cannot be empty");
        }
    }
}

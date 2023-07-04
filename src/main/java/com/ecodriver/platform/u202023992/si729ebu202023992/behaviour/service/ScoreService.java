package com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.service;

import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.dto.ScoreRequestDto;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.model.Score;

import java.util.Map;

public interface ScoreService {
    Score createScore(ScoreRequestDto scoreRequestDto);
    Map<String, Object> getAverageScore(Long driverId);
    Map<String, Object> getMaxScore(Long driverId);
}

package com.ecodriver.platform.u202023992.si729ebu202023992.common.mapper;

import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.dto.ScoreRequestDto;
import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.model.Score;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperMethods {
    private final ModelMapper modelMapper;
    public MapperMethods() {
        this.modelMapper = new ModelMapper();
    }

    public Score toScore(ScoreRequestDto scoreRequestDto){
        return modelMapper.map(scoreRequestDto, Score.class);
    }
}

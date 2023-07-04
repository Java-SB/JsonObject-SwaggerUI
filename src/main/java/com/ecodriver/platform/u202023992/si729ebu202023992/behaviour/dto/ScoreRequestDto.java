package com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequestDto {

    private Long driverId;

    private float value;

    @Schema(hidden = true)
    private Date registeredAt;
}

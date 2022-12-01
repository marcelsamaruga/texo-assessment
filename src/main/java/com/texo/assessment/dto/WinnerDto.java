package com.texo.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinnerDto {
    String producer;
    Integer interval;
    Integer previousWin;
    Integer followingWin;
}

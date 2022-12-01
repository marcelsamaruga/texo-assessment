package com.texo.assessment.controller;

import com.texo.assessment.dto.WinnersDto;
import com.texo.assessment.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WinnersController {

    private final MovieService service;

    @GetMapping("/winners")
    public ResponseEntity<WinnersDto> getWinners() {
        return ResponseEntity.ok(service.getWinners());
    }

}

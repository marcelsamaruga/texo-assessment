package com.texo.assessment.service;

import com.texo.assessment.dto.WinnerDto;
import com.texo.assessment.dto.WinnersDto;
import com.texo.assessment.entity.Movie;
import com.texo.assessment.repository.MovieRepository;
import com.texo.assessment.util.WinnerConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final WinnerConverter converter;

    @Transactional(readOnly = true)
    public WinnersDto getWinners() {
        // get records from database into a projection
        var winnersProjection = repository.getGoldenRaspberryWinners();

        // transform the projection into dto
        var winners = winnersProjection.stream()
                .map(converter::toWinnerDto)
                .collect(Collectors.toList());

        var minInterval = winners.stream()
                .mapToInt(WinnerDto::getInterval)
                .min()
                .orElse(0);

        var maxInterval = winners.stream()
                .mapToInt(WinnerDto::getInterval)
                .max()
                .orElse(Integer.MAX_VALUE);

        return WinnersDto.builder()
                .min(getWinners(minInterval, winners))
                .max(getWinners(maxInterval, winners))
                .build();
    }

    private List<WinnerDto> getWinners(int numberToCompare, List<WinnerDto> winners) {
        return winners.stream()
                .filter(winner -> winner.getInterval() == numberToCompare)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(List<Movie> movies) {
        repository.saveAll(movies);
    }
}

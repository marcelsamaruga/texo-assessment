package com.texo.assessment.config;

import com.opencsv.CSVParser;
import com.opencsv.CSVReaderBuilder;
import com.texo.assessment.entity.Movie;
import com.texo.assessment.entity.Producer;
import com.texo.assessment.entity.Studio;
import com.texo.assessment.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MoviesLoader {

    private final ResourceLoader resourceLoader;
    private final MovieService movieService;
    private final CSVParser csvParser;

    @Value("${csv.file}")
    private String filePath;

    @SneakyThrows
    @PostConstruct
    public void init() {
        Resource csvFile = resourceLoader.getResource(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(csvFile.getInputStream());

        var csvReader = new CSVReaderBuilder(inputStreamReader)
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build();

        String[] record;
        List<Movie> movies = new ArrayList<>();

        while ((record = csvReader.readNext()) != null) {
            var movie = Movie.builder()
                    .year(Integer.valueOf(record[0]))
                    .title(record[1])
                    .studios(strToListObj(record[2], (n) -> Studio.builder().name(n).build()))
                    .producers(strToListObj(record[3], (n) -> Producer.builder().name(n).build()))
                    .winner(YesNoEnum.strToBoolean(record[4]))
                    .build();

            movies.add(movie);
        }

        movieService.save(movies);
    }

    private <T> List<T> strToListObj(String value, Function<String, T> function) {
        return Arrays.stream(value.split(" and |,"))
                .filter(name -> value != null && !"".equals(name))
                .map(name -> {
                    var obj = function.apply(Objects.requireNonNull(name).trim());
                    return obj;
                })
                .collect(Collectors.toList());
    }
}

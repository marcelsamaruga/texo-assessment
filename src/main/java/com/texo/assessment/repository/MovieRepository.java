package com.texo.assessment.repository;

import com.texo.assessment.dto.WinnerProjectionDto;
import com.texo.assessment.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    String WINNERS =
            " SELECT (MAX(year) - MIN(year)) gap,  MAX(year) followingWin, MIN(year) previousWin, producer.name producer FROM movie " +
            "   INNER JOIN movie_producers ON movie_producers.movie_id = movie.id " +
            "   INNER JOIN producer ON movie_producers.producers_id = producer.id " +
            " WHERE winner = TRUE " +
            " GROUP BY producer.name " +
            " HAVING gap > 0 ";

    @Query(value = WINNERS, nativeQuery = true)
    List<WinnerProjectionDto> getGoldenRaspberryWinners();

}

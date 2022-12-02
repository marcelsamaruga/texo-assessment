package com.texo.assessment;

import com.texo.assessment.repository.MovieRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.doThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WinnersControllerTests {

	@LocalServerPort
	private int port;

	@SpyBean
	private MovieRepository repository;

	@BeforeEach
	void setup() {
		RestAssured.port = port;
	}

	@Test
	@DisplayName("GIVEN list of movies from the database WHEN endpoint /winners is invoked THEN it should response successfully")
	public void it_should_return_winners_successfully() {
		given()
		.when()
		.get("/winners")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("max[0].producer", equalTo("Matthew Vaughn"))
			.body("max[0].interval", equalTo(13))
			.body("max[0].previousWin", equalTo(2002))
			.body("max[0].followingWin", equalTo(2015))
			.body("min[0].producer", equalTo("Bo Derek"))
			.body("min[0].interval", equalTo(6))
			.body("min[0].previousWin", equalTo(1984))
			.body("min[0].followingWin", equalTo(1990));
	}

	@Test
	@DisplayName("GIVEN an error WHEN endpoint /winners is invoked THEN it should respond bad gateway")
	public void it_should_respond_bad_gateway() {
		doThrow(RuntimeException.class).when(repository).getGoldenRaspberryWinners();

		given()
				.when()
				.get("/winners")
				.then()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

}

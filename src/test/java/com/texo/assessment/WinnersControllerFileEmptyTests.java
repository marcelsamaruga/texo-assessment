package com.texo.assessment;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "csv.file=classpath:static/movielist-empty.csv")
class WinnersControllerFileEmptyTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	void setup() {
		RestAssured.port = port;
	}

	@Test
	@DisplayName("GIVEN an empty database WHEN endpoint /winners is invoked THEN should return empty values")
	public void it_should_return_winners_successfully() {
		given()
		.when()
		.get("/winners")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("max", empty())
			.body("min", empty());
	}

}

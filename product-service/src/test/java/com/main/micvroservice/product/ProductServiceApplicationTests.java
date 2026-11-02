package com.main.micvroservice.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.mongodb.MongoDBContainer;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	// Using Spring Boot's ServiceConnection managed start (No @Testcontainers needed)
	@ServiceConnection
	static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	static {
		mongoDBContainer.start();
	}

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreate() {
		String requestBody = """
                {
                  "name": "Wireless Noise-Canceling Mic",
                  "description": "Premium over-ear MIC with active noise cancellation and 50-hour battery life.",
                  "price": 249.99
                }
                """;

		RestAssured.given()
				.contentType("application/json") // Added content type so Spring knows it's JSON
				.accept("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Wireless Noise-Canceling Mic"))
				.body("price", Matchers.equalTo(249.99f)); // Floating points in RestAssured often match as Floats
	}
}

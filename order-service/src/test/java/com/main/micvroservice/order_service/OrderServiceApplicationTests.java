package com.main.micvroservice.order_service;

import com.main.micvroservice.order_service.stubs.InventoryClientStub;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.mysql.MySQLContainer;
import io.restassured.RestAssured;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({
		// Replace 'customer-service.url' with the actual configuration key your FeignClient uses
		@ConfigureWireMock(name = "inventory-service", baseUrlProperties = "inventory.url")
})
class OrderServiceApplicationTests {

    @ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port=port;
	}
	static{
		mySQLContainer.start();
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderjson = """
				{
				  "skucode": "SKU-IPHONE-15",
				  "price": 79999,
				  "quantity": 2
				}
				""";
		InventoryClientStub.stubInventoryCall("SKU-IPHONE-15",2);
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderjson)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201)
				// Extract and verify the raw body response directly 👇
				.body(Matchers.equalTo("Order Placed Successfully"));
	}

}

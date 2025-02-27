package com.udemy.integrationtest.swagger;

import com.udemy.ConfigsTest;
import com.udemy.integrationtest.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldDisplaySwaggerUiPage(){
        String context = given().basePath("/swagger-ui/index.html")
                .port(ConfigsTest.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Assertions.assertTrue(context.contains("Swagger UI"));
    }
}

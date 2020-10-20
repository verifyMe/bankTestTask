package weather.test;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import weather.WeatherTest;
import weather.data.WeatherURI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Error codes")
public class ErrorWeatherTest extends WeatherTest {

    private final static ResponseSpecification errorRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .expectBody("success",equalTo(false))
            .log(LogDetail.BODY)
            .build();

    @Test
    @DisplayName("GET Request to /current, invalid query parameter value")
    public void testWrongCity(){
        given()
                .spec(requestSpec)
                .basePath(WeatherURI.CURRENT_ENDPOINT)
                .and()
                .param("query", "Geneve123")
            .when()
                .get()
            .then()
                .spec(errorRespSpec)
                .and()
                .body("error.code", equalTo(615))
                .body("error.type", equalTo("request_failed"))
                .body("error.info", equalTo("Your API request failed. Please try again or contact support."));

    }

    @Test
    @DisplayName("GET request to /current no access_key specified")
    public void testNoAccessKey(){
        given()
                .spec(requestSpec)
                .basePath(WeatherURI.CURRENT_ENDPOINT)
                .param("access_key","")
                .when()
                .get()
                .then()
                .spec(errorRespSpec)
                .and()
                .body("error.code", equalTo(101))
                .body("error.type", equalTo("missing_access_key"))
                .body("error.info", equalTo("You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]"));

    }

    @Test
    @DisplayName("GET request to /historical not covered by subscription plan")
    public void testPlanNotSupported(){
        given()
                .spec(requestSpec)
                .basePath(WeatherURI.HISTORICAL_ENDPOINT)
                .param("query","Geneve")
                .when()
                .get()
                .then()
                .spec(errorRespSpec)
                .and()
                .body("error.code", equalTo(603))
                .body("error.type", equalTo("historical_queries_not_supported_on_plan"))
                .body("error.info", equalTo("Your current subscription plan does not support historical weather data. Please upgrade your account to use this feature."));

    }

    @Test
    @DisplayName("GET Request to /current, no required query parameter specified")
    public void testMissingRequiredParameter(){
        given()
                .spec(requestSpec)
                .basePath(WeatherURI.CURRENT_ENDPOINT)
                .when()
                .get()
                .then()
                .spec(errorRespSpec)
                .and()
                .body("error.code", equalTo(601))
                .body("error.type", equalTo("missing_query"))
                .body("error.info", equalTo("Please specify a valid location identifier using the query parameter."));

    }
}

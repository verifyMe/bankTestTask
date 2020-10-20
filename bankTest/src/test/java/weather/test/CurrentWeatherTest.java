package weather.test;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import weather.data.WeatherURI;
import weather.util.Utils;
import weather.WeatherTest;
import weather.data.WeatherTestData;

import java.io.IOException;
import java.util.*;

import static org.junit.runners.Parameterized.*;
import static io.restassured.RestAssured.*;

@RunWith(Parameterized.class)
@DisplayName("Current weather suite")
public class CurrentWeatherTest extends WeatherTest {

    private final static ResponseSpecification respSpec = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.BODY)
            .build();

    private final static String[] cities = {"Moscow", "Saint-Petersburg","New-York", "Geneve"};

    private String city;
    private WeatherTestData expectedData;

    public CurrentWeatherTest(String city, WeatherTestData expectedData){
        this.city = city;
        this.expectedData = expectedData;
    }

    @Test
    @DisplayName("GET Request to \"/current\" with city")
    public void testGetRequestCurrentWeather(){
        WeatherTestData testData =
                given()
                    .spec(requestSpec)
                    .and()
                    .basePath(WeatherURI.CURRENT_ENDPOINT)
                    .and()
                    .param("query", city)
                .when()
                    .get()
                .then()
                    .spec(respSpec)
                    .extract()
                    .body().as(WeatherTestData.class);
        assertEqualToExpected(testData, expectedData);
    }

    @Parameters
    public static Collection<Object[]> getTestData() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        for(String city : cities) {
            String fileName = city.toLowerCase() +".json";
            testData.add(new Object[]{city, Utils.readTestData(fileName, WeatherTestData.class)});
        }
        return testData;
    }

}

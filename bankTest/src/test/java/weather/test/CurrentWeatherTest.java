package weather.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import weather.data.WeatherURI;
import weather.util.Utils;
import weather.WeatherTest;
import weather.data.WeatherTestData;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static weather.util.MapMatcher.*;

@DisplayName("Current weather test suite")
public class CurrentWeatherTest extends WeatherTest {

    private final static String[] cities = {"Moscow", "Saint-Petersburg","New-York", "Geneve"};


    @ParameterizedTest(name = "GET \"/current\" with query = {0}")
    @MethodSource("testData")
    public void testGetRequestCurrentWeather(String city, WeatherTestData expectedData){
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
                    .body("request", matchesTheMap(expectedData.getRequest()))
                    .body("location", matchesTheMap(expectedData.getLocation()))
                    .body("current", matchesTheMap(expectedData.getCurrent()));;

    }

    public static Stream<Object[]> testData() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        for(String city : cities) {
            String fileName = city.toLowerCase() +".json";
            testData.add(new Object[]{city, Utils.readTestData(fileName, WeatherTestData.class)});
        }
        return testData.stream();
    }

}

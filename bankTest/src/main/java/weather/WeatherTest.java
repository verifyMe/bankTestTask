package weather;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import weather.data.WeatherURI;

import org.apache.http.HttpStatus;


public abstract class WeatherTest {
    protected static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(WeatherURI.BASE_URI)
            .addParam("access_key", WeatherURI.ACCESS_KEY)
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .log(LogDetail.PARAMS)
            .build();

    protected final static ResponseSpecification respSpec = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.BODY)
            .build();


}

package weather;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import weather.data.WeatherTestData;
import weather.data.WeatherURI;
import weather.util.MapMatcher;

import java.util.Map;

import org.apache.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;

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


    protected void assertEqualToExpected(Map<?, ?> tested, Map<?,?> expected){
        expected.entrySet()
                .forEach(entry-> {
                            assertThat(tested, hasKey(entry.getKey()));
                            assertThat("For the field "+entry.getKey(),
                                    tested.get(entry.getKey()),
                                    equalTo(entry.getValue()));
                        }
                );
    }

    protected void assertEqualToExpected(WeatherTestData tested, WeatherTestData expected){
        assertThat(tested.getLocation(), new MapMatcher(expected.getLocation()));
        assertEqualToExpected(tested.getRequest(), expected.getRequest());
        assertEqualToExpected(tested.getLocation(), expected.getLocation());
        assertEqualToExpected(tested.getCurrent(), expected.getCurrent());
    }
}

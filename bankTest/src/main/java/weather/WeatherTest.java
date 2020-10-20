package weather;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import weather.data.WeatherTestData;
import weather.data.WeatherURI;

import java.util.Map;

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
        assertEqualToExpected(tested.getRequest(), expected.getRequest());
        assertEqualToExpected(tested.getLocation(), expected.getLocation());
        assertEqualToExpected(tested.getCurrent(), expected.getCurrent());
    }
}

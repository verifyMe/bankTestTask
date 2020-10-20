package weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class WeatherTestData {
    @JsonProperty("request")
    private Map<String,Object> request;

    @JsonProperty("location")
    private Map<String,Object> location;

    @JsonProperty("current")
    private Map<String,Object> current;
}

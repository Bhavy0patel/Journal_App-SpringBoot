package com.Anonymous.journal.api.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;
        @JsonProperty("weather_descriptions")

        private List<String> weatherDescriptions;
        private int feelslike;
    }

}





package com.Anonymous.journal.service;

import com.Anonymous.journal.Cache.AppCache;
import com.Anonymous.journal.api.responce.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherService {

    @Value("${weather.api.key}")
    private static String apikey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired // by App Cache we are fetching api
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apikey>", apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

        WeatherResponse body = response.getBody();
        return body;
    }
}

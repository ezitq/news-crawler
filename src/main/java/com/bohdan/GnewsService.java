package com.bohdan;

import com.bohdan.entity.GNewsResponse;
import com.bohdan.entity.NewsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

@Service
public class GnewsService {

    @Value("${GNEWS_API_KEY}")
    private String secretApiKey;


    private final RestTemplate restTemplate = new RestTemplate();

    public GNewsResponse getLastNewsAccordingToTopic(NewsRequest newsRequest) {

        String url = urlBuilder(newsRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                response.getBody(),
                GNewsResponse.class
        );
    }

    private String urlBuilder(NewsRequest newsRequest) {
        StringBuilder url = new StringBuilder("https://gnews.io/api/v4/top-headlines?apikey=%s".formatted(secretApiKey));

        if (newsRequest.country() != null) {
            url.append("&country=%s".formatted(newsRequest.country()));
        }
        if (newsRequest.language() != null) {
            url.append("&lang=%s".formatted(newsRequest.language()));
        }
        if (newsRequest.topic() != null) {
            url.append("&q=%s".formatted(newsRequest.topic()));
        }
        if (newsRequest.maxArticles() != null) {
            int maxArt = newsRequest.maxArticles();
            if (newsRequest.maxArticles() <= 0 || newsRequest.maxArticles() > 10) {
                maxArt = 1;
            }

            url.append("&max=%d".formatted(maxArt));
        }

        return url.toString();
    }
}

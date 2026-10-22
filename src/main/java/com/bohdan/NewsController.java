package com.bohdan;

import com.bohdan.entity.GNewsResponse;
import com.bohdan.entity.NewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {

    private final GnewsService gnewsService;

    @Autowired
    public NewsController(GnewsService gnewsService) {
        this.gnewsService = gnewsService;
    }

    @GetMapping("/news")
    public ResponseEntity<GNewsResponse> fetch(@RequestBody NewsRequest newsRequest){
        return ResponseEntity.ok(gnewsService.getLastNewsAccordingToTopic(newsRequest));
    }

}

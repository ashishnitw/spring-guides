package com.ashishnitw.consumingrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PostController {

    @GetMapping("/posts")
    public Post posts(@RequestParam(value = "id", defaultValue = "World") String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gorest.co.in/public/v2/posts/" + id;
        ResponseEntity<Post> responseEntity = restTemplate.getForEntity(url, Post.class);
        return responseEntity.getBody();
    }
}

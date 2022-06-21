package com.agustinvon.JibberJabber.service;

import com.agustinvon.JibberJabber.model.responses.Follow;
import com.agustinvon.JibberJabber.model.responses.FollowResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public FollowResponse getFollows(String url, String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authHeader);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<FollowResponse> response = this.restTemplate.exchange(url, HttpMethod.GET, request, FollowResponse.class, 1);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new NoSuchElementException();
        }
    }
}

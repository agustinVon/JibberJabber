package com.agustinvon.JibberJabber.service;

import com.agustinvon.JibberJabber.model.responses.AccessTokenResponse;
import com.agustinvon.JibberJabber.model.responses.Follow;
import com.agustinvon.JibberJabber.model.responses.FollowResponse;
import com.agustinvon.JibberJabber.model.responses.UserReply;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    public String getAdminAccessToken(String clientSecret, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id","admin-cli");
        map.add("grant_type","client_credentials");
        map.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessTokenResponse> response =
                restTemplate.exchange(url,
                        HttpMethod.POST,
                        entity,
                        AccessTokenResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return  response.getBody().getAccess_token();
        } else {
            throw new NoSuchElementException();
        }
    }

    public UserReply getUserInformation(String url, String adminToken, String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + adminToken);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<UserReply> response =
                this.restTemplate.exchange(url + "/" + userId,
                        HttpMethod.GET, request,
                        UserReply.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new NoSuchElementException();
        }
    }

    public UserReply[] getAllUsersInformation(String url, String adminToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + adminToken);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<UserReply[]> response =
                this.restTemplate.exchange(url,
                        HttpMethod.GET, request,
                        UserReply[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new NoSuchElementException();
        }
    }
}

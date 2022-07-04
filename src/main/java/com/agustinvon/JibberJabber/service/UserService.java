package com.agustinvon.JibberJabber.service;

import com.agustinvon.JibberJabber.model.dto.UserDTO;
import com.agustinvon.JibberJabber.model.responses.UserReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private RestService restService;
    @Value("${client.secret}")
    private String clientSecret;
    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    @Value("${keycloak.realm}")
    private String keycloakRealm;

    public UserService(RestService restService) {
        this.restService = restService;
    }

    public UserDTO getUserInformation(String userId) {
        String token = restService.getAdminAccessToken(clientSecret, keycloakUrl + "/realms/master/protocol/openid-connect/token");
        UserReply user = restService.getUserInformation(keycloakUrl + "/admin/realms/" + keycloakRealm + "/users/", token, userId);
        return new UserDTO(userId, user.getFirstName(), user.getUsername(), "placeholder");
    }
}

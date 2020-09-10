package com.example.general.configs;


import lombok.Data;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Data
@Component
public class TokenProvider {

    public String getTokenValue(OAuth2Authentication oAuth2Authentication) {
        return ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
    }
}

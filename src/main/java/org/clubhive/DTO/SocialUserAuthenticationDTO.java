package org.clubhive.DTO;

public class SocialUserAuthenticationDTO {
    private String socialToken;
    private AuthType authType;


    enum AuthType{
        GOOGLE,FACEBOOK
    }
}

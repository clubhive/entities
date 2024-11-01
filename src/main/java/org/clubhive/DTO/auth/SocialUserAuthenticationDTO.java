package org.clubhive.DTO.auth;

import lombok.Getter;
import lombok.Setter;
import org.clubhive.enums.AuthType;

@Getter
@Setter
public class SocialUserAuthenticationDTO {
    private String socialToken;
    private AuthType authType;
}

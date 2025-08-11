package org.clubhive.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.clubhive.dto.UserResponseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KeeperResponseDTO extends UserResponseDTO {

    private long id;
    private String keeperId;
    private String phone;
    private long organizerId;
}

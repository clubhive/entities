package org.clubhive.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.clubhive.dto.UserResponseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleKeeperDTO extends UserResponseDTO {

    private String keeperId;
    private String phone;
}

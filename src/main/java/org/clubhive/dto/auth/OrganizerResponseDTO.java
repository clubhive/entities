package org.clubhive.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.clubhive.dto.UserResponseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerResponseDTO extends UserResponseDTO {
    private long id;
    private String organizerId;
    private String urlPay;
    private String picture;
}

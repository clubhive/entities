package org.clubhive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerDTO extends UserAuthentication{
    private long id;
    private String name;
    private String urlPay;
    private String picture;
}

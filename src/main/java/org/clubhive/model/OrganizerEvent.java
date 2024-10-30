package org.clubhive.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerEvent {

    String name;
    String email;
    String picture;
    String organizerId;
}

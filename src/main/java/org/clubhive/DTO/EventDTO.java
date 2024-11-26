package org.clubhive.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private String name;
    private String desc;
    private LocalTime time;
    private LocalDate date;
    private Boolean state;
    private String location;
    private String img;
    private long cityId;
    private OrganizerEventDTO organizerEvent;
    private String id;
    private List<TicketDTO> tickets;
    private List<PromoterDTO> promoters;
    private Double lowerPrice;
}

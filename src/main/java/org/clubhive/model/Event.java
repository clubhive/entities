package org.clubhive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;

    private String name;

    private String desc;

    private LocalDate date;

    private LocalTime time;

    private long cityId;

    private String location;

    private String img;

    private Boolean state;

    private OrganizerEvent organizerEvent;

    private List<Promoter> promoters;

    private List<Ticket> tickets;

    private Double lowerPrice;
}

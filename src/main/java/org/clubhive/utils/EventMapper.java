package org.clubhive.utils;


import org.clubhive.DTO.EventDTO;
import org.clubhive.DTO.OrganizerEventDTO;
import org.clubhive.DTO.PromoterDTO;
import org.clubhive.DTO.TicketDTO;
import org.clubhive.entities.CityEntity;
import org.clubhive.entities.EventEntity;
import org.clubhive.model.Event;
import org.clubhive.model.OrganizerEvent;
import org.clubhive.model.Promoter;
import org.clubhive.model.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventMapper {

    public static Event mapEventEntityToEventModel(EventEntity eventEntity) {

        Event event = GenericMapper.map(eventEntity, Event.class);
        event.setCityId(eventEntity.getCity().getId());
        event.setLocation(event.getLocation());
        event.setOrganizerEvent(GenericMapper.map(eventEntity.getOrgnzId(), OrganizerEvent.class));

        return event;
    }

    public static EventDTO mapEventModelToEventDTO(Event event) {
        return GenericMapper.map(event, EventDTO.class);
    }

    public static List<Event> mapEventEntityListToEventList(List<EventEntity> eventEntityList) {
        return eventEntityList.stream()
                .map(EventMapper::mapEventEntityToEventModel).toList();
    }

    public static List<EventDTO> mapEventListToEventDTOList(List<Event> eventList) {
        return eventList.stream()
                .map(EventMapper::mapEventModelToEventDTO)
                .toList();
    }

    public static Event mapEventDTOToEvent(EventDTO eventDTO) {
        Event event = GenericMapper.map(eventDTO, Event.class);
        List<Promoter> promoters = null;
        List<Ticket> tickets = null;
        if (eventDTO.getPromoters() != null)
            promoters = GenericMapper.mapList(eventDTO.getPromoters(), Promoter.class);
        if (eventDTO.getTickets() != null)
            tickets = GenericMapper.mapList(eventDTO.getTickets(), Ticket.class);
        event.setPromoters(promoters);
        event.setTickets(tickets);
        event.setCityId(eventDTO.getCityId());
        return event;
    }

    public static EventDTO mapEventToEventDTO(Event event) {
        EventDTO eventDTO = GenericMapper.map(event, EventDTO.class);
        List<PromoterDTO> promoters = GenericMapper.mapList(event.getPromoters(), PromoterDTO.class);
        List<TicketDTO> tickets = GenericMapper.mapList(event.getTickets(), TicketDTO.class);
        eventDTO.setPromoters(promoters);
        eventDTO.setTickets(tickets);
        eventDTO.setOrganizerEvent(GenericMapper.map(event.getOrganizerEvent(), OrganizerEventDTO.class));
        return eventDTO;
    }

    public static EventEntity mapEventToEventEntity(Event event) {
        EventEntity eventEntity = new EventEntity();

        eventEntity.setId(event.getId());
        eventEntity.setName(event.getName());
        eventEntity.setDate(LocalDate.parse(event.getDate()));
        eventEntity.setImg(event.getImg());
        eventEntity.setDesc(event.getDesc());
        eventEntity.setTime(LocalTime.parse(event.getTime()));
        eventEntity.setState(event.getState());
        eventEntity.setCity(new CityEntity(event.getId(), ""));
        eventEntity.setLocation(event.getLocation());
        eventEntity.setLowerPrice(event.getLowerPrice());

        return eventEntity;
    }
}

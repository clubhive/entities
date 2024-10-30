package org.clubhive.repositories.implement;

import FindUser.FindUser;
import exceptions.NoBugsException;
import lombok.RequiredArgsConstructor;
import org.clubhive.entities.EventEntity;
import org.clubhive.entities.OrganizerEntity;
import org.clubhive.model.Event;
import org.clubhive.model.Organizer;
import org.clubhive.model.OrganizerEvent;
import org.clubhive.repositories.jpa.CityRepositoryJpa;
import org.clubhive.repositories.jpa.EventRepositoryJpa;
import org.clubhive.repositories.jpa.OrganizerRepository;
import org.clubhive.utils.EventMapper;
import org.clubhive.utils.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EventRepositoryJpa eventRepositoryJpa;
    private final OrganizerRepoImpl organizerRepository;
    private final CityRepositoryJpa cityRepositoryJpa;

    public Event save(Event event) {
        Organizer organizer = organizerRepository.findByOrganizerId(event.getOrganizerEvent().getOrganizerId());
        EventEntity eventEntity = EventMapper.mapEventToEventEntity(event);
        eventEntity.setOrgnzId(GenericMapper.map(organizer, OrganizerEntity.class));
        eventEntity.setCity(cityRepositoryJpa.findById(event.getCityId()).orElseThrow());
        return EventMapper.mapEventEntityToEventModel(eventRepositoryJpa.save(eventEntity));
    }

    public List<Event> findAllByOrgnz(String organizerId){

        Organizer organizer = organizerRepository.findByOrganizerId(organizerId);

        return EventMapper.mapEventEntityListToEventList(eventRepositoryJpa.findAllByOrgnzId(GenericMapper.map(organizer, OrganizerEntity.class)));
    }

    public Event findById(Long id) {
        if (id == null)
            throw new NoBugsException("Id must not be null", HttpStatus.BAD_REQUEST);

        EventEntity eventFounded = null;
        try {
             eventFounded = eventRepositoryJpa.findById(id).orElse(null);
            if (eventFounded == null) {
                throw new NoBugsException("Event not found", HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            throw new NoBugsException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        Event event = EventMapper.mapEventEntityToEventModel(eventFounded);
        event.setOrganizerEvent(GenericMapper.map(eventFounded.getOrgnzId(), OrganizerEvent.class));

        return event;
    }

    public Event findByIdAndSubject(Long id,String subject){
        if (id == null || subject == null)
            throw new NoBugsException("Id and subject must not be null", HttpStatus.BAD_REQUEST);

        EventEntity eventFounded = null;

        try {
            eventFounded = eventRepositoryJpa.findByIdAndAndOrgnzId(id, subject);
            if (eventFounded == null) {
                throw new NoBugsException("Event not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            throw new NoBugsException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        Event event = EventMapper.mapEventEntityToEventModel(eventFounded);
        event.setOrganizerEvent(GenericMapper.map(eventFounded.getOrgnzId(), OrganizerEvent.class));

        return event;
    }

    public List<Event> filterEvents(String search){

        List<EventEntity> events = null;

        try{
            events = eventRepositoryJpa.filterEvents(search);

            if (events.isEmpty()) {
                throw new NoBugsException("Events not found", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            throw new NoBugsException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return EventMapper.mapEventEntityListToEventList(events);
    }

}

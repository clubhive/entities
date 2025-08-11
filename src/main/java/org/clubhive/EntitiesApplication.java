package org.clubhive;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.clubhive.model.Event;
import org.clubhive.repositories.implement.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

import com.co.nobugs.nobugsexception.NoBugsException;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class EntitiesApplication {

    private final EventRepository eventRepository;

    public static void main(String[] args) throws NoBugsException {

        ApplicationContext applicationContext = SpringApplication.run(EntitiesApplication.class, args);
        applicationContext.getBean(EntitiesApplication.class).test();

    }

    public void test() throws NoBugsException {
        List<Event> events = eventRepository.filterEvents("Villavicencio");
        log.info("Events in Villavicencio: {}", events);
    }
}

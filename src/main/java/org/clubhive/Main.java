package org.clubhive;

import lombok.RequiredArgsConstructor;

import org.clubhive.model.Event;
import org.clubhive.model.Organizer;
import org.clubhive.repositories.implement.DetailRepository;
import org.clubhive.repositories.implement.EventRepository;
import org.clubhive.repositories.implement.OrganizerRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.naming.Context;

@SpringBootApplication
@RequiredArgsConstructor
public class Main {

    @Autowired
    private EventRepository eventRepository;

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        applicationContext.getBean(Main.class).test();

    }

    public void test(){
        Event event = eventRepository.findAllByOrgnz("f498a488-7051-7071-aea1-70dee1ea0343").get(0);

        System.out.println(event);
    }
}

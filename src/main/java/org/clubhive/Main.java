package org.clubhive;

import lombok.RequiredArgsConstructor;

import org.clubhive.model.Organizer;
import org.clubhive.repositories.implement.DetailRepository;
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
    private OrganizerRepoImpl organizerRepo;

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        applicationContext.getBean(Main.class).test();

    }

    public void test(){
        Organizer organizer = new Organizer();
        organizer.setId(28);
        organizer.setName("Test");
        organizer.setUrlPay("Test");
        organizer.setOrganizerId("Test");
        organizer.setPicture("Test");
        organizerRepo.save(organizer);
    }
}

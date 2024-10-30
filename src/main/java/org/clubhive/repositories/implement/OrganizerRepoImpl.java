package org.clubhive.repositories.implement;



import exceptions.NoBugsException;
import lombok.RequiredArgsConstructor;
import org.clubhive.entities.OrganizerEntity;
import org.clubhive.model.Organizer;
import org.clubhive.repositories.UserRepositoryImplementation;
import org.clubhive.repositories.jpa.OrganizerRepository;
import org.clubhive.utils.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
public class OrganizerRepoImpl implements UserRepositoryImplementation<Organizer> {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public Organizer save(Organizer organizer){

        OrganizerEntity organizerEntity = GenericMapper.map(organizer,OrganizerEntity.class);

        return GenericMapper.map(organizerRepository.save(organizerEntity),Organizer.class);
    }

    @Override
    public List<Organizer> findAll(){
        return GenericMapper.mapList(organizerRepository.findAll(),Organizer.class);
    }

    @Override
    public Organizer update(Organizer organizer){

        OrganizerEntity organizerEntity = organizerRepository.findByOrganizerId(organizer.getOrganizerId());

        organizerEntity.setUrlPay((organizer.getUrlPay() != null) ? organizer.getUrlPay() : organizerEntity.getUrlPay());
        organizerEntity.setName((organizer.getName() != null) ? organizer.getName() : organizerEntity.getName());

        return GenericMapper.map(organizerRepository.save(organizerEntity),Organizer.class);
    }

    public Organizer findById(Long id){

        return GenericMapper.map(organizerRepository.findById(id).orElseThrow(()->new NoBugsException("Organizer not found", HttpStatus.NOT_FOUND)),Organizer.class);
    }

    public Organizer findByOrganizerId(String organizerId){
        return Stream.of(organizerRepository.findByOrganizerId(organizerId)).filter(Objects::nonNull).map(org -> GenericMapper.map(org,Organizer.class)).findAny().orElseThrow(()->new NoBugsException("Organizer not found", HttpStatus.NOT_FOUND));
    }
}

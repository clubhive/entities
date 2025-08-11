package org.clubhive.repositories.implement;

import java.util.List;

import org.clubhive.entities.KeeperEntity;
import org.clubhive.entities.OrganizerEntity;
import org.clubhive.model.Keeper;
import org.clubhive.model.Organizer;
import org.clubhive.repositories.UserRepositoryImplementation;
import org.clubhive.repositories.jpa.KeeperRepository;
import org.clubhive.utils.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.co.nobugs.nobugsexception.NoBugsException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KeeperRepoImpl implements UserRepositoryImplementation<Keeper> {

    private final KeeperRepository keeperRepository;
    private final OrganizerRepoImpl organizerRepository;

    @Override
    public Keeper update(Keeper model) throws NoBugsException {
        Keeper keeperToUpdate = null;
        try {
            keeperToUpdate = findByUserId(model.getKeeperId());

            if (keeperToUpdate == null)
                throw new NullPointerException();
        } catch (Exception e) {
            throw new NoBugsException("Keeper not found", HttpStatus.NOT_FOUND);
        }

        keeperToUpdate.setPhone(model.getPhone() == null ? keeperToUpdate.getPhone() : model.getPhone());
        keeperToUpdate.setEmail(model.getEmail() == null ? keeperToUpdate.getEmail() : model.getEmail());
        keeperToUpdate.setName(model.getName() == null ? keeperToUpdate.getName() : model.getName());

        return save(keeperToUpdate);
    }

    @Override
    public Keeper save(Keeper model) throws NoBugsException {
        KeeperEntity keeperToSave = GenericMapper.map(model, KeeperEntity.class);
        keeperToSave.setOrganizer(GenericMapper.map(organizerRepository.findById(model.getOrganizerId()), OrganizerEntity.class));

        return GenericMapper.map(keeperRepository.save(keeperToSave),Keeper.class);
    }

    @Override
    public List<Keeper> findAll() {
       return GenericMapper.mapList(keeperRepository.findAll(), Keeper.class);

    }

    @Override
    public Keeper findByEmail(String email) {
        return GenericMapper.map(keeperRepository.findByEmail(email), Keeper.class);
    }

    @Override
    public Keeper findByUserId(String userId) {
        return GenericMapper.map(keeperRepository.findByKeeperId(userId), Keeper.class);
    }

    public List<Keeper> findAllByOrganizer(Organizer organizer) {
        return GenericMapper.mapList(keeperRepository.findAllByOrganizer(GenericMapper.map(organizer, OrganizerEntity.class)), Keeper.class);
    }

    public Keeper findById(Long id) throws NoBugsException {
        return GenericMapper.map(keeperRepository.findById(id).orElseThrow(()->new NoBugsException("Keeper not found", HttpStatus.NOT_FOUND)),Keeper.class);
    }
}

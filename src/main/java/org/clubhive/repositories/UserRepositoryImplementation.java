package org.clubhive.repositories;

import org.clubhive.model.User;

import java.util.List;

import com.co.nobugs.nobugsexception.NoBugsException;

public interface UserRepositoryImplementation<T extends User> {

    T update(T model) throws NoBugsException;
    T save(T model) throws NoBugsException;
    List<T> findAll();
    T findByEmail(String email);
    T findByUserId(String userId);
}

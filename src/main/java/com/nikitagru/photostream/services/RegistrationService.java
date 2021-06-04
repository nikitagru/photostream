package com.nikitagru.photostream.services;

import com.nikitagru.photostream.repositories.UsersRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private UsersRoleRepository usersRoleRepository;

    @Autowired
    public void setUsersRoleRepository(UsersRoleRepository usersRoleRepository) {
        this.usersRoleRepository = usersRoleRepository;
    }

    public void saveAndSetRoleUser(long userId) {
        usersRoleRepository.saveNewUser(userId);
    }
}

package com.nikitagru.photostream.services;

import com.nikitagru.photostream.repositories.UsersRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * Service for registration
 */
@Service
public class RegistrationService {

    private UsersRoleRepository usersRoleRepository;

    @Autowired
    public void setUsersRoleRepository(UsersRoleRepository usersRoleRepository) {
        this.usersRoleRepository = usersRoleRepository;
    }

    /***
     * Saves new user with role
     * @param userId current user's id
     */
    public void saveAndSetRoleUser(long userId) {
        usersRoleRepository.saveNewUser(userId);
    }
}

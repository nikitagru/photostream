package com.nikitagru.photostream.repositories;

import com.nikitagru.photostream.entities.User;
import com.nikitagru.photostream.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/***
 * Repository for user's roles
 */
@Repository
public interface UsersRoleRepository extends JpaRepository<UserRole, Long> {
    /***
     * Give to user role
     * @param id current user's id
     */
    @Query(value = "INSERT INTO users_roles (user_id, role_id) VALUES (:#{#id}, 1)", nativeQuery = true)
    @Modifying
    @Transactional
    void saveNewUser(@Param("id") long id);
}

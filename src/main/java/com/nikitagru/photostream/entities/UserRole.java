package com.nikitagru.photostream.entities;

import com.nikitagru.photostream.entities.id.UserRoleId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/***
 * User's roles table
 */
@Entity
@Data
@Table(name = "users_roles")
@IdClass(UserRoleId.class)
public class UserRole {

    @Id
    private long user_id;
    @Id
    private long role_id;
}

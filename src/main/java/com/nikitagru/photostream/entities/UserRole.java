package com.nikitagru.photostream.entities;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users_roles")
public class UserRole {
    @Id
    private long user_id;

    private long role_id;
}

package com.nikitagru.photostream.entities;

import java.io.Serializable;

public class UserRoleId implements Serializable {
    private long user_id;
    private long role_id;

    public UserRoleId() {
    }

    public UserRoleId(long user_id, long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }
}

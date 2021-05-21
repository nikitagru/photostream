package com.nikitagru.photostream.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users_posts")
@IdClass(UserPostId.class)
public class UserPost {

    @Id
    private Long user_id;

    @Id
    private Long post_id;
}

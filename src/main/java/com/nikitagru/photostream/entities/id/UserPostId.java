package com.nikitagru.photostream.entities.id;

import javax.persistence.Id;
import java.io.Serializable;

/***
 * Ids for table "users_posts"
 */
public class UserPostId implements Serializable {
    private Long user_id;
    private Long post_id;

    public UserPostId() {
    }

    public UserPostId(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}

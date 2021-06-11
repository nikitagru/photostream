package com.nikitagru.photostream.entities.id;

import java.io.Serializable;

public class SubscriptionId implements Serializable {
    private Long user_id;
    private Long subscriber_id;

    public SubscriptionId() {
    }

    public SubscriptionId(Long user_id, Long subscriber_id) {
        this.user_id = user_id;
        this.subscriber_id = subscriber_id;
    }
}

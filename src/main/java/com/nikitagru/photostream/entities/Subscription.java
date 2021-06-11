package com.nikitagru.photostream.entities;

import com.nikitagru.photostream.entities.id.SubscriptionId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "subscribers")
@IdClass(SubscriptionId.class)
public class Subscription {
    @Id
    private long user_id;

    @Id
    private long subscriber_id;
}

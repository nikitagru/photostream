package com.nikitagru.photostream.services;

import com.nikitagru.photostream.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * Service for subscription
 */
@Service
public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /***
     * Handles subscribe
     * @param userId current user's id
     * @param subscriptionId current subscription user's id
     */
    public void subscribe(long userId, long subscriptionId) {
        subscriptionRepository.subscribe(userId, subscriptionId);
    }

    /***
     * Handles unsubscribe
     * @param userId current user's id
     * @param subscriptionId current unsubscribe user's id
     */
    public void unSubscribe(long userId, long subscriptionId) {
        subscriptionRepository.unSubscribe(userId, subscriptionId);
    }
}

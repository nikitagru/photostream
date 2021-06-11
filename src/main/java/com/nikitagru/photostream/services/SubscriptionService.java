package com.nikitagru.photostream.services;

import com.nikitagru.photostream.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void subscribe(long userId, long subscriptionId) {
        subscriptionRepository.subscribe(userId, subscriptionId);
    }

    public void unSubscribe(long userId, long subscriptionId) {
        subscriptionRepository.unSubscribe(userId, subscriptionId);
    }
}

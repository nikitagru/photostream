package com.nikitagru.photostream.repositories;

import com.nikitagru.photostream.entities.Subscription;
import com.nikitagru.photostream.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/***
 * Repository for subscriptions
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    /***
     * Inserts into table "subscriptions" new value (creates new subscription)
     * @param userId current user's id
     * @param subscriptionId current subscription's id
     */
    @Query(value = "INSERT INTO subscribers (user_id, subscriber_id) VALUES (:#{#userid}, :#{#subid})", nativeQuery = true)
    @Modifying
    @Transactional
    void subscribe(@Param("userid") long userId, @Param("subid") long subscriptionId);

    /***
     * Deletes from table "subscriptions" value (handles unsubscribe)
     * @param userId current user's id
     * @param subscriptionId current subscriber's id, which user wont to unsubscribe
     */
    @Query(value = "DELETE FROM subscribers WHERE user_id = :userid AND subscriber_id = :subid", nativeQuery = true)
    @Modifying
    @Transactional
    void unSubscribe(@Param("userid") long userId, @Param("subid") long subscriptionId);
}

package dao;


import model.Subscription;

import java.util.Collection;

public interface SubscriptionDao {
    void createSubscription(int userId, int subId);
    void deleteSubscription(int userId, int subId);
    Collection<Subscription> getUserSubscriptions(int userId);
    Collection<Integer> getFollowers(int userId);
}

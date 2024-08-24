package com.axidAcid.service;

import com.axidAcid.model.PlanType;
import com.axidAcid.model.Subscription;
import com.axidAcid.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}

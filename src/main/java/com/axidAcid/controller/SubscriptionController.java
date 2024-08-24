package com.axidAcid.controller;

import com.axidAcid.model.PlanType;
import com.axidAcid.model.Subscription;
import com.axidAcid.model.User;
import com.axidAcid.service.SubscriptionService;
import com.axidAcid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(@RequestParam("Authorization") String jwt)throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.getUserSubscription(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(@RequestParam("Authorization") String jwt,
                                                            @RequestParam PlanType planType)throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(),planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }



}

package com.axidAcid.controller;

import com.axidAcid.model.PlanType;
import com.axidAcid.model.User;
import com.axidAcid.request.PaymenntLinkResponse;
import com.axidAcid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    @Autowired
    private UserService userService;

    public ResponseEntity<PaymenntLinkResponse> createPaymentLink(@PathVariable PlanType planType, @RequestHeader("Authorization") String jwt) throws Exception {

        User user= userService.findUserProfileByJwt(jwt);
        PaymenntLinkResponse paymenntLinkResponse = new PaymenntLinkResponse("CXXXXX","xxxxx");
        return new ResponseEntity<>(paymenntLinkResponse, HttpStatus.CREATED);

    }

}

package com.axidAcid.service;

import com.axidAcid.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    public void sendInvitaion(String email, Long projectId) throws MessagingException;

    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String userEmail) throws Exception;

    void deleteToken(String token) throws Exception;
}

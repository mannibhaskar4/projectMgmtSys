package com.axidAcid.service;

import com.axidAcid.model.Invitation;
import com.axidAcid.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitaion(String email, Long projectId) throws MessagingException {

        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        String invitationLink = "http://localhost:5173/accept_inviation?token"+invitationToken;

        emailService.sendEmailWithToken(email,invitationLink);

    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation==null){
            throw new Exception("Invalid inviation token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) throws Exception {
        Invitation invitation= invitationRepository.findByEmail(userEmail);
        if(invitation==null){
            throw new Exception("Invalid inviation token");
        }
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation==null){
            throw new Exception("Invalid inviation token");
        }
        invitationRepository.delete(invitation);
    }
}

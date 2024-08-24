package com.axidAcid.controller;

import com.axidAcid.model.Chat;
import com.axidAcid.model.Message;
import com.axidAcid.model.User;
import com.axidAcid.request.CreateCommentRequest;
import com.axidAcid.request.CreateMessageRequest;
import com.axidAcid.service.MessageService;
import com.axidAcid.service.ProjectService;
import com.axidAcid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest req)throws Exception{
        User user = userService.findUserById(req.getSenderId());
//        if(user == null) throw new Exception("User not found with id "+req.getSenderId());
        Chat chat = projectService.getProjectById(req.getProjectId()).getChat();
        if(chat == null) throw new Exception("Chat not found");
        Message sentMessage = messageService.sendMessage(req.getSenderId(), req.getProjectId(), req.getContent());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId)throws Exception{
        List<Message> messages = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}

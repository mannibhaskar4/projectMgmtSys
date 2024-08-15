package com.axidAcid.controller;

import com.axidAcid.Response.AuthResponse;
import com.axidAcid.Response.MessageResponse;
import com.axidAcid.model.Issue;
import com.axidAcid.model.IssueDTO;
import com.axidAcid.model.User;
import com.axidAcid.request.IssueRequest;
import com.axidAcid.service.IssueService;
import com.axidAcid.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    Logger LOGGER= LoggerFactory.getLogger(IssueController.class);

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getUserById(@PathVariable Long issueId)throws Exception{
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId)throws Exception{
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,@RequestParam("Authorization") String token)throws Exception{
        LOGGER.info("issue--------- "+issue);
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());

        Issue createdIssue=issueService.createIssue(issue, tokenUser);
        IssueDTO issueDTO=new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setPriority(createdIssue.getPriority());
//            issueDTO.setProjectId(cr);
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setAssignee(createdIssue.getAssignee());

        return ResponseEntity.ok(issueDTO);


    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                    @RequestHeader("Authorization") String token)throws Exception{

        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());

        MessageResponse response= new MessageResponse();
        response.setMessage("Issue Deleted");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,@PathVariable Long userId)throws Exception{
        Issue issue=issueService.addUserToIssue(issueId,userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable String status,@PathVariable Long issueId)throws Exception{
        return ResponseEntity.ok(issueService.updateStatus(issueId,status));
    }



}

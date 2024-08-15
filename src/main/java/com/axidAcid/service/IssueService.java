package com.axidAcid.service;

import com.axidAcid.model.Issue;
import com.axidAcid.model.User;
import com.axidAcid.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;


    void deleteIssue(Long issueId,Long userId)throws Exception;

    List<User> getAssigneeForIssue(Long issueId)throws Exception;

    Issue addUserToIssue(Long issueId, Long userId)throws Exception;

    Issue updateStatus(Long issueId, String status)throws Exception;

}

package com.axidAcid.service;

import com.axidAcid.model.Comments;
import com.axidAcid.model.Issue;
import com.axidAcid.model.User;
import com.axidAcid.repository.CommentsRepository;
import com.axidAcid.repository.IssueRepository;
import com.axidAcid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comments createComments(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("issue not found with id"+issueId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user not found with id"+userId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comments comments = new Comments();

        comments.setIssue(issue);
        comments.setUser(user);
        comments.setCreatedDateTime(LocalDateTime.now());
        comments.setContent(content);

        Comments savedComment = commentsRepository.save(comments);

        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComments(Long commentId, Long userId) throws Exception {
        Optional<Comments> commentsOptional = commentsRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentsOptional.isEmpty()){
            throw new Exception("comment not found with id "+commentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user not found with id "+userId);
        }

        Comments comments = commentsOptional.get();
        User user = userOptional.get();

        if(comments.getUser().equals(user)){
            commentsRepository.delete(comments);
        }else {
            throw new Exception("User does not have permission to delete this comment!");
        }

    }

    @Override
    public List<Comments> findCommentsByIssuesId(Long issueId) {
        return commentsRepository.findByIssueId(issueId);
    }
}

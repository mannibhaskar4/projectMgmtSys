package com.axidAcid.service;

import com.axidAcid.model.Comments;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface CommentService {

    Comments createComments(Long issueId,Long userId,String content ) throws Exception;

    void deleteComments(Long commentId, Long userId) throws Exception;

    List<Comments> findCommentsByIssuesId(Long issueId);

}

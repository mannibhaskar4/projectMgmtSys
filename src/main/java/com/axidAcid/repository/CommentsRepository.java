package com.axidAcid.repository;

import com.axidAcid.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    List<Comments> findByIssueId(Long issueId);


}

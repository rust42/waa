package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByStudentId(int id);
}

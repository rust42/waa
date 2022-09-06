package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAllComments(int id);

    void creatNewComment(CommentDTO commentDTO, int id);
}

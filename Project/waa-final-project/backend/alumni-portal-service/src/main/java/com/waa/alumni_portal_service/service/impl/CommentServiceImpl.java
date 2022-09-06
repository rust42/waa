package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.CommentDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.entity.Comment;
import com.waa.alumni_portal_service.repository.CommentRepository;
import com.waa.alumni_portal_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDTO> findAllComments(int id) {
        var result = new ArrayList<CommentDTO>();
        commentRepository.findAllByStudentId(id).forEach(comment -> result.add(modelMapper.map(comment, CommentDTO.class)));
        return result;
    }

    @Override
    public void creatNewComment(CommentDTO commentDTO, int id) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(id);
        commentDTO.setStudent(studentDTO);
        commentRepository.save(modelMapper.map(commentDTO, Comment.class));
    }
}

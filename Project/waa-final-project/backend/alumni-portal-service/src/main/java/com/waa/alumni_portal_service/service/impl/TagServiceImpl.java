package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.TagDTO;
import com.waa.alumni_portal_service.repository.TagRepository;
import com.waa.alumni_portal_service.service.TagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final ModelMapper modelMapper;

    private final TagRepository tagRepository;


    @Override
    public List<TagDTO> findAll() {
        var result = new ArrayList<TagDTO>();
        tagRepository.findAll().forEach(tag -> result.add(modelMapper.map(tag, TagDTO.class)));
        return result;
    }
}

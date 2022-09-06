package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> findAll();
}

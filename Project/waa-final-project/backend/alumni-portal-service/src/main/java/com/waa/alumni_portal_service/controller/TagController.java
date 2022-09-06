package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.TagDTO;
import com.waa.alumni_portal_service.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@CrossOrigin
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public List<TagDTO> findAll() {
        return tagService.findAll();
    }
}

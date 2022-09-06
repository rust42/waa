package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.CkUploadDTO;
import com.waa.alumni_portal_service.service.UploadStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@CrossOrigin
@RequiredArgsConstructor
public class FileController {

    @Value("${config.url}")
    private String serverUrl;
    private final UploadStorageService uploadStorageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@ModelAttribute MultipartFile upload) throws Exception {
        String uploadPath = uploadStorageService.store(upload);
        CkUploadDTO ckUpload = new CkUploadDTO();
        String filePath =  serverUrl + "/files/";
        ckUpload.setUrl(filePath + uploadPath);
        return ResponseEntity.ok(ckUpload);
    }

    @GetMapping("/{path}")
    public ResponseEntity<byte[]> getFile(@PathVariable String path) throws IOException {
        Path filePath = uploadStorageService.getPath(path);
        byte[] fileData = Files.readAllBytes(uploadStorageService.getPath(path));
        MediaType mediaType = MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(filePath.toFile()));
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(fileData);
    }
}

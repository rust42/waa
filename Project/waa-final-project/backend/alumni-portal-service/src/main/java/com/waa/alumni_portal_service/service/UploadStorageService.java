package com.waa.alumni_portal_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface UploadStorageService {

    String store(MultipartFile file) throws IOException;

    Path getPath(String path);
}

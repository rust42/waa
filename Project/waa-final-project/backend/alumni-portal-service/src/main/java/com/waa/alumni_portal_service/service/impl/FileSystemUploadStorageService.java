package com.waa.alumni_portal_service.service.impl;


import com.waa.alumni_portal_service.service.UploadStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class FileSystemUploadStorageService implements UploadStorageService {
    @Value("${config.upload-dir}")
    private String UPLOAD_DIR;

    @Override
    public String store(MultipartFile file) throws IOException {
        Optional<String> extension = getExtensionByStringHandling(file.getOriginalFilename());

        var path = Paths.get(UPLOAD_DIR);

        Path directoryPath = Files.createDirectories(path);

        Date date = new Date();
        long epoch = date.getTime();

        String newFileName = String.format("%d", epoch);

        if (extension.isPresent()) {
            newFileName = String.format("%d.%s", epoch, extension.get());
        }

        Path storingPath = Path.of(directoryPath.toString(), newFileName);

        var is = file.getInputStream();

        Files.copy(is, storingPath);
        return newFileName;
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    @Override
    public Path getPath(String path){
        var uploadDir = Paths.get(UPLOAD_DIR);
        var filePath = Path.of(uploadDir.toString(), path);
        return filePath;
    }
}

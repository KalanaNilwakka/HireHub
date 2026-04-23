package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.service.FileStorageService;
import com.Kalana.HireHub.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String uploadDir;
    private final CommonUtils commonUtils;

    public FileStorageServiceImpl(CommonUtils commonUtils){
        this.commonUtils = commonUtils;
        this.uploadDir = "uploads/resumes/";
    }

    @Override
    public String storeFile(MultipartFile resume, Long jobId) throws IOException {

        File dir = new File(uploadDir);

        if (!dir.exists())
            dir.mkdirs();

        String fileName = commonUtils.getLoggedInUser().getUsername() + "_" + jobId + "_" + resume.getOriginalFilename();
        Path path = Paths.get(uploadDir,fileName);

        Files.copy(resume.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString();
    }
}

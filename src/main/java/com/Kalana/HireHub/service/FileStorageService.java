package com.Kalana.HireHub.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String storeFile(MultipartFile resume, Long jobId) throws IOException;
}

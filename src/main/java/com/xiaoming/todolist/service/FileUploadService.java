package com.xiaoming.todolist.service;

import com.xiaoming.todolist.model.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    ApiResponse<String> uploadFile(MultipartFile file);
}

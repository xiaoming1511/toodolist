package com.xiaoming.todolist.service.impl;

import com.xiaoming.todolist.model.ApiResponse;
import com.xiaoming.todolist.service.FileUploadService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Data//为成员变量生成get和set方法
@ConfigurationProperties(prefix = "oss")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Override
    public ApiResponse<String> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.error(400, "文件为空，请选择文件");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 确保文件名安全
        if (fileName != null) {
            fileName = sanitizeFileName(fileName);
        }

        // 设置文件保存路径（这里保存到项目根目录下的upload文件夹中）
        String uploadDir = System.getProperty("user.dir") + "/upload/";
        File uploadPath = new File(uploadDir);

        // 如果目录不存在则创建
        if (!uploadPath.exists() && !uploadPath.mkdirs()) {
            logger.error("目录创建失败，路径：{}", uploadDir);
            return ApiResponse.error(500, "目录创建失败，请检查路径：" + uploadDir);
        }

        try {
            // 保存文件
            String filePath = uploadDir + fileName;
            file.transferTo(new File(filePath));

            // 返回成功响应
            return ApiResponse.success("文件上传成功", filePath);
        } catch (IOException e) {
            logger.error("文件上传失败，原因：", e);
            return ApiResponse.error(500, "文件上传失败：" + e.getMessage());
        }
    }

    private String sanitizeFileName(String fileName) {
        // 去掉文件名中的路径部分，只保留文件名
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }
}

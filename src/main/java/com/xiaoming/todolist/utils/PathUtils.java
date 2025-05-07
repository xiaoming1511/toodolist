package com.xiaoming.todolist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PathUtils {

    // 支持的文件类型白名单
    private static final String[] SUPPORTED_FILE_TYPES = {".jpg", ".jpeg", ".png", ".gif", ".xlsx", ".xls", ".csv", ".txt"};

    /**
     * 生成文件存储路径
     * @param rootDir 根目录路径
     * @param fileName 原始文件名
     * @return 生成的文件路径，如果文件类型不支持则返回null
     */
    public static String generateFilePath(String rootDir, String fileName) {
        // 根据日期生成路径   2025/04/21/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());

        // 获取文件扩展名
        String fileType = "";
        String baseName = fileName; // 默认使用原始文件名
        if (fileName != null && fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            baseName = fileName.substring(0, index); // 文件名（不包含扩展名）
            fileType = fileName.substring(index); // 文件扩展名

            // 检查文件类型是否支持
            boolean isSupported = false;
            for (String supportedType : SUPPORTED_FILE_TYPES) {
                if (fileType.equalsIgnoreCase(supportedType)) {
                    isSupported = true;
                    break;
                }
            }

            if (!isSupported) {
                // 如果文件类型不支持，记录日志并返回null
                System.err.println("不支持的文件类型：" + fileType);
                return null;
            }
        }

        // 为了避免文件名冲突，可以在文件名后附加时间戳
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = timestampFormat.format(new Date());

        // 拼接最终路径
        return rootDir + datePath + baseName + "_" + timestamp + fileType;
    }
}

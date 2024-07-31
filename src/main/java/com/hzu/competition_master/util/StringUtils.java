package com.hzu.competition_master.util;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    /**
     * 获取文件名
     */
    public static String getFileName(MultipartFile file) {
        // 得到上传文件的原始文件名
        String filename = file.getOriginalFilename();

        // 返回uuid.文件类型，如：20220618131456.jpg
        return uuid() + getFileType(filename);
    }

    /**
     * 通过文件名获取文件类型
     */
    public static String getFileType(String fileName) {
        // 得到文件名中最后一次出现"."的位置
        int index = fileName.lastIndexOf('.');

        // 文件类型统一转换为小写
        return fileName.substring(index).toLowerCase();
    }

    /**
     * 根据当前时间生成UUID
     * @return String
     */
    public static String uuid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDate = LocalDateTime.now();

        return localDate.format(formatter);
    }
}

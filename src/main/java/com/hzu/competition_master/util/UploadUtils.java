package com.hzu.competition_master.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class UploadUtils {
    /**
     * 图片上传
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = StringUtils.getFileName(file);
        // 保存文件
        file.transferTo(new File("C:\\Users\\11987\\Desktop\\CompetitionMaster\\src\\main\\resources\\static\\certificatePicture\\", fileName));
        return "certificatePicture/" + fileName;
    }
}
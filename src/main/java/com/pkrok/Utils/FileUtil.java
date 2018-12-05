package com.pkrok.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static Boolean isNotEmpty(MultipartFile multipartFile) {
        return multipartFile != null && !multipartFile.getOriginalFilename().isEmpty() && multipartFile.getSize() > 0;
    }
}

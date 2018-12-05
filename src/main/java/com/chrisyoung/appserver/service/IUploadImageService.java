package com.chrisyoung.appserver.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: imageuploademo
 * @author: Chris Young
 * @create: 2018-12-05 14:14
 * @description:
 **/

public interface IUploadImageService {
    String UploadImage(MultipartFile img, String uId);
}

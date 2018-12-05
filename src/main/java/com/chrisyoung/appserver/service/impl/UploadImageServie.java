package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.constant.UploadImageParams;
import com.chrisyoung.appserver.dao.AppUserDao;
import com.chrisyoung.appserver.service.IUploadImageService;
import com.chrisyoung.appserver.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: imageuploademo
 * @author: Chris Young
 * @create: 2018-12-05 14:15
 * @description:
 **/

@Service
public class UploadImageServie implements IUploadImageService {

    private String basePath;

    private final AppUserDao appUserDao;

    @Autowired
    public UploadImageServie(AppUserDao appUserDao) {
        basePath=UploadImageParams.BASE_PATH;
        this.appUserDao = appUserDao;
    }

    @Override
    public String UploadImage(MultipartFile img, String uId) {
        //String uid = UUID.randomUUID().toString().replace("-", "");
        if (img.isEmpty()) {
            return "";
        }

        String contentType = img.getContentType();
        if (!contentType.contains("")) {
            return "";
        }

        String returnPath = ImageUtil.getFilePath(uId);
        String filePath = basePath + returnPath;
        if(img.getSize()>UploadImageParams.MAX_REQUEST_SIZE){
           return "";
        }
        String fileName = "";
        try {
            fileName = ImageUtil.saveImg(img, filePath, uId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String photoPath=returnPath + File.separator + fileName;
        int result=appUserDao.updateUserPhoto(uId,photoPath);
        if(result==1){
            return photoPath;
        }else{
            return "";
        }

    }
}

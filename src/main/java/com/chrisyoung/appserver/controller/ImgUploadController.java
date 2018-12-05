package com.chrisyoung.appserver.controller;

import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.dto.Result;
import com.chrisyoung.appserver.service.IUploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-05 15:16
 * @description: 图片上传
 **/

@RestController
public class ImgUploadController {
    private final IUploadImageService uploadImageService;

    @Autowired

    public ImgUploadController(IUploadImageService uploadImageService) {
        this.uploadImageService = uploadImageService;
    }

    @PostMapping("/img/upload")
    public Result uploadImg(@RequestParam("image") MultipartFile img, @RequestHeader("UserId") String uId){
        String imgPath=uploadImageService.UploadImage(img,uId);
        if(imgPath.equals("")){
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }else{
            return Result.success(imgPath);
        }
    }

}

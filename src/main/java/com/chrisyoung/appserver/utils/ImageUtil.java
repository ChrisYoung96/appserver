package com.chrisyoung.appserver.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @program: imageuploademo
 * @author: Chris Young
 * @create: 2018-12-05 09:17
 * @description: 图片上传展示类
 **/


public class ImageUtil {
    public static String saveImg(MultipartFile multipartFile, String path, String uId) throws IOException {
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        FileInputStream fileInputStream=(FileInputStream) multipartFile.getInputStream();
        String fileName="HeadPhoto"+uId+".png";
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(path+File.separator+fileName));
        byte[] bs=new byte[1024];
        int len;
        while ((len=fileInputStream.read(bs))!=-1){
            bos.write(bs,0,len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    /**
     * 获取图片上传的路径
     *
     * @param
     * @return
     */
    public static String getFilePath(String uId) {
        //处理图片
        String path = File.separator + "headPhoto"
                + File.separator + uId;
        return path;
    }

    /**
     * 图片压缩
     *
     * @param srcImg 需要压缩的图片
     * @param rate   压缩比例
     */
    public static File reduceImg(File srcImg, String path,float rate) {
        File tagFile=new File(path);
        try {
            Image image = ImageIO.read(srcImg);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if (width != 0 && height != 0) {
                //如果宽高不为空，则开始压缩图片
                BufferedImage tagImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tagImg.getGraphics().drawImage(
                        image.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                        0, 0, null);
                ImageIO.write(tagImg, ".jpg", tagFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagFile;
    }

    /**
     * 判断文件是不是图片
     *
     * @param img
     * @return
     */
    public static final boolean isImage(File img) {
        boolean flag = false;
        try {
            BufferedImage image = ImageIO.read(img);
            int width = image.getWidth();
            int height = image.getHeight();
            if (width == 0 || height == 0) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}

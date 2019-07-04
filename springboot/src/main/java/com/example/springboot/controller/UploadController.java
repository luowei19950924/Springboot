package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * @author luowei
 * @date 2019/5/13 15:23
 */
@Controller
public class UploadController {

    @RequestMapping("/index")
    public String toUpload() {
        return "upload";
    }

    @RequestMapping("/indexs")
    public String toUploads() {
        return "uploads";
    }

    /**
     * 单文件上传
     * @author luowei
     * @date 2019/5/13 15:40
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        try {
            //创建文件在服务器端的存放路径
            String dir = request.getServletContext().getRealPath("/upload");
            File fileDir = new File(dir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //生成文件在服务器存放的名字
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + fileSuffix;
            File files = new File(fileDir + "/" + fileName);
            //上传
            file.transferTo(files);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    /**
     * 多文件上传
     * @author luowei
     * @date 2019/5/13 15:47
     */
    @RequestMapping(value = "upload/batch", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFiles(MultipartFile[] file, HttpServletRequest request) {
        try {
            //创建文件在服务器端的存放路径
            String dir = request.getServletContext().getRealPath("/upload");
            File fileDir = new File(dir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //生成文件在服务器存放的名字
            for(int i=0;i<file.length;i++){
                String fileSuffix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
                String fileName = UUID.randomUUID().toString() + fileSuffix;
                File files = new File(fileDir + "/" + fileName);
                //上传
                file[i].transferTo(files);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}

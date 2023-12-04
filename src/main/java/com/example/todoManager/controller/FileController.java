package com.example.todoManager.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    Logger log = LoggerFactory.getLogger(FileController.class);
//    the limit to upload the file is 1MB we can inc the size in application properties
    @PostMapping("/singleFileUpload")
    public String processSingleFile(@RequestParam("single") MultipartFile multipartFile){
        log.info("File name: {}",multipartFile.getName());
        log.info("File size: {}",multipartFile.getSize());
        return "Got the file";
    }
    @PostMapping("/multiFileUpload")
    public String processMultiFile(@RequestParam("multi") MultipartFile[] multipartFiles){
       for(MultipartFile multipartFile : multipartFiles){
           log.info("File name: {}",multipartFile.getName());
           log.info("File size: {}",multipartFile.getSize());
       }
        return "Got "+ multipartFiles.length + " Files";
    }

    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response) {
        try{
            InputStream image = new FileInputStream("image/img.jpeg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(image,response.getOutputStream());
        }
        catch (Exception exception){
           log.info("Exception: {}",exception.getMessage());
        }

    }

}

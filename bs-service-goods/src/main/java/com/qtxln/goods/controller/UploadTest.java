package com.qtxln.goods.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.qtxln.transport.InvokerResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author QT
 * 2018-07-30 18:13
 */
@RestController
public class UploadTest {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAIi11bzO1F3IWm";
    String accessKeySecret = "g1JRyVRGz5AlKZ6YwyPYFEmGM3aRlj";
    String bucketName = "bs-image";
    String fileHost = "https://bs-image.oss-cn-beijing.aliyuncs.com/";

    @PostMapping("upload")
    public InvokerResult upload(MultipartFile file) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = file.getInputStream();
        ossClient.putObject(bucketName, file.getOriginalFilename(), inputStream);
        return InvokerResult.getInstance(fileHost + file.getOriginalFilename());
    }
}

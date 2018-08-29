package com.qtxln.util;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author QT
 * 2018-07-30 18:53
 */
public class AliOSSUtil {
    private final static String END_POINT = "http://oss-cn-beijing.aliyuncs.com";
    private final static String ACCESS_KEY_ID = "LTAIi11bzO1F3IWm";
    private final static String ACCESS_KEY_SECRET = "g1JRyVRGz5AlKZ6YwyPYFEmGM3aRlj";
    private final static String BUCKET_NAME = "bs-image";
    private final static String FILE_HOST = "https://bs-image.oss-cn-beijing.aliyuncs.com/";

    public static String upload(MultipartFile file) {
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try (InputStream inputStream = file.getInputStream()) {
            ossClient.putObject(BUCKET_NAME, file.getOriginalFilename(), inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return FILE_HOST + file.getOriginalFilename();
    }
}

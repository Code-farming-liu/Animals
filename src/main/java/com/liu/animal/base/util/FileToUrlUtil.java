package com.liu.animal.base.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUtil
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/4/28 10:11
 **/

public class FileToUrlUtil {
    public static String uploadImage(MultipartFile multipartFile) {
        String imgUrl = "http://47.95.252.130";
        //上传图片到服务器
        //获得配置文件的路径
        //配置fdfs的全局链接地址
//        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();
//        String tracker = new ClassPathResource("/tracker.conf").toString();
        try {
            ClientGlobal.init("/opt/tracker.conf");

            TrackerClient trackerClient = new TrackerClient();
            //获得trackerServer的实例
            TrackerServer trackerServer = trackerClient.getTrackerServer();

            //通过tracker获得一个storage链接客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);

            //获得文件后缀名
            //定义一个正则表达式的筛选规则，为了获取图片的类型
            //获得文件后缀名
            String originalFilename = multipartFile.getOriginalFilename();//a.jpg

            int lastIndexOf = originalFilename.lastIndexOf(".");

            String extName = originalFilename.substring(lastIndexOf + 1);

            byte[] bytes = multipartFile.getBytes();//获得上传的二进制对象

            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);
            for (String uploadInfo : uploadInfos) {
                imgUrl += "/" + uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
//    public static void main(String[] args) {
//        String str = "data:image/png;base64,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";
//        uploadImage(str);
//    }
}
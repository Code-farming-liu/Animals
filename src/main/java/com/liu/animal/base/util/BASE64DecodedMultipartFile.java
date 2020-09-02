package com.liu.animal.base.util;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

/**
 * @ClassName: Base64DecodeMultipartFile
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/4/28 13:30
 **/

public class BASE64DecodedMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private final String header;

    public BASE64DecodedMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    /**
     *      * base64转multipartFile
     *      *
     *      * @param base64
     *      * @return
     *      
     */
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(1111);
            return null;
        }
    }

    public static void main(String[] args) {
        String str = "data:image/png;base64,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";
        base64ToMultipart(str);
    }
}

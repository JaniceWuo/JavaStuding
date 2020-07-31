package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
public class UploadService {
    private static final List<String> content_types = Arrays.asList("image/gif","image/jpeg","image/png");
    private static final Logger logger = LoggerFactory.getLogger( UploadService.class );
    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
        //校验文件类型
        String contentType = file.getContentType();
//        System.out.println(contentType);
        if(!content_types.contains( contentType )){
            logger.info( "文件类型不合法:" + originalFilename );
            return null;
        }
        //校验文件内容
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read( file.getInputStream() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(bufferedImage == null){
            logger.info( "文件内容不合法{}",originalFilename );
            return null;
        }
        //保存到服务器
        try {
//            file.transferTo( new File("E:\\" + originalFilename) );
            //返回url  进行回显

//            return "http://image.leyou.com/" + originalFilename;
            String ext = StringUtils.substringAfterLast( originalFilename,"." );
            StorePath storePath = this.storageClient.uploadFile( file.getInputStream(), file.getSize(), ext, null );
            System.out.println(storePath.getFullPath());
            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            logger.info( "服务器内部错误：" + originalFilename );
            e.printStackTrace();
        }
        return null;
    }
}

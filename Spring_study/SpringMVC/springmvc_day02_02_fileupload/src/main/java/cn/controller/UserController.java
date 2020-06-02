package cn.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //使用fileupload组件
        String path = request.getSession().getServletContext().getRealPath("/uploads/");  //上传的图片所存储的地方
//        System.out.println(path);
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()) file.mkdir();
        //解析request对象  获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        for(FileItem item:items){
            if(item.isFormField()){
                //说明是普通文件项
            }else {
                //说明是上传文件项
                String fileName = item.getName();
                //把文件的名称设置唯一值  uuid  这样即使上传同一张图片多次 也不会覆盖
                String uuid = UUID.randomUUID().toString().replace("-","");
                fileName = uuid + "_" + fileName;

                item.write(new File(path,fileName));
                item.delete();  //删除临时文件
            }
        }

        return "success";
    }

    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springMVC文件上传");
        //使用fileupload组件
        String path = request.getSession().getServletContext().getRealPath("/uploads/");  //上传的图片所存储的地方
//        System.out.println(path);
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()) file.mkdir();

        String fileName = upload.getOriginalFilename();
                //把文件的名称设置唯一值  uuid  这样即使上传同一张图片多次 也不会覆盖
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid + "_" + fileName;
        upload.transferTo(new File(path,fileName));
        return "success";
    }


    @RequestMapping("/fileUpload3")
    public String fileUpload3(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传");
        //定义上传文件服务器的路径
        String path = "http://localhost:80/fileUploadServer/uploads/";

        String fileName = upload.getOriginalFilename();
        //把文件的名称设置唯一值  uuid  这样即使上传同一张图片多次 也不会覆盖
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid + "_" + fileName;
        //创建客户端的对象
        Client client = Client.create();
        //和图片服务器进行连接
        WebResource webResource = client.resource(path + fileName);
        //上传文件
        webResource.put(upload.getBytes());
        return "success";
    }
}

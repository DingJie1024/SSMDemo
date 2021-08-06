package edu.dj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/load")
public class DownloadController {

    @RequestMapping("/toDownload")
    public String toDownloadPage(){
        return "download";
    }


    /*
     * @RequestParam("file")将name=file控件得到的文件封装成CommonsMultipartFile对象
     * */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //如果文件名为空，直接返回首页
        if ("".equals(fileName)){
            return "redirect:/index.jsp";
        }

        //上传路径保存
        String path = request.getServletContext().getRealPath("/upload");
        System.out.println("======>"+path);
        //路径不存在
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }

        InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(new File(realPath, fileName));

        //读去写出
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))!=-1){
            os.write(bytes,0,len);
            os.flush();
        }
        os.close();
        is.close();
        return "redirect:/index.jsp";
    }

    @PostMapping("/upload2")
    public String uploadFile2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

        //上传路径保存
        String path = request.getServletContext().getRealPath("/upload");

        //路径不存在
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }

        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));

        return "redirect:/index.jsp";
    }

    @RequestMapping("/download")
    public String downloadFile(HttpServletResponse response, HttpServletRequest request) throws IOException {

        //下载文件地址
        String path = request.getServletContext().getRealPath("/download");
        String fileName = "img_title.jpg";

        //1.设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fileName,"utf-8"));

        File file = new File(path,fileName);
        //2.读取文件-输入流
        InputStream is = new FileInputStream(file);
        //3.写出文件-输出流
        OutputStream os = response.getOutputStream();

        byte[] bytes = new byte[1024];
        int index = 0;
        //4.执行写出操作
        while ((index=is.read(bytes))!=-1){
            os.write(bytes,0,index);
            os.flush();
        }
        os.close();
        is.close();
        return null;

    }
}

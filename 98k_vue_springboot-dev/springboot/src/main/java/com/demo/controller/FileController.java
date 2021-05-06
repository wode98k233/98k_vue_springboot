package com.demo.controller;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    public UUID uuid = UUID.randomUUID();
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Value("${prop.upload-foldertext}")
    private String UPLOAD_FOLDERTEXT;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/uploadFile")
    public String uploadHtml(@RequestParam(name = "html", required = false) MultipartFile file,
                             HttpServletRequest request) {
        if (file == null) {
            return "请选择要上传的文件";
        }
        if (file.getSize() > 1024 * 1024 * 30) {
            return "文件大小不能大于30M";
        }
        //获取文件后缀
        //String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        /*if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return "请选择jpg,jpeg,gif,png格式的图片";
        }*/
        String savePath = UPLOAD_FOLDERTEXT;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = file.getOriginalFilename();
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            //return  ""+savePath+filename;
            return  "保存文件异常";
        }
        //返回文件名称
        return filename;
    }
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            return "请选择要上传的图片";
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            return "文件大小不能大于10M";
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return "请选择jpg,jpeg,gif,png格式的图片";
        }
        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            //return  ""+savePath+filename;
            return  "保存文件异常";
        }
        //返回文件名称
        return filename;
    }
    public void write(String path,String filename,String lines)
            throws IOException {
        BufferedWriter pw = null;
        try {
            FileOutputStream fos =  new FileOutputStream(path + filename + ".txt", false);//true表明会追加内容
            pw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            pw.write(lines);
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/uploadText")
    public String uploadText(@RequestParam("name")String filename
            ,@RequestParam("textarea2") String lines) {
        if (lines == null) {
            return "请填写博文内容";
        }
        //获取保存路径
        String savePath = UPLOAD_FOLDERTEXT;
        //通过UUID生成唯一文件名

        try {
            //将文件保存指定目录
            write(savePath,filename,lines);
        } catch (Exception e) {
            e.printStackTrace();
            return  "保存文件异常";
        }
        //返回文件名称
        return savePath+filename+".txt"+"\t";
    }
    @DeleteMapping("/deleteFilePic")
    public String deleteFilePic(@RequestParam("path")String path){
        File file = new File(UPLOAD_FOLDER+path);
        if (file.exists()){//文件是否存在
             file.delete();//删除文件
            return "删除"+path+"成功";
        }
        return "删除"+path+"失败";
    }
    @DeleteMapping("/deleteFileText")
    public String deleteFileText(@RequestParam("path")String path){
        File file = new File(UPLOAD_FOLDERTEXT+path);
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
            return "删除"+path+"成功";
        }
        return "删除"+path+"失败";
    }
    @RequestMapping("/download")
    public Object downloadFile(@RequestParam String fileName, final HttpServletResponse response, final HttpServletRequest request){
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("text/html;charset=GBK");//application/x-download为txt格式
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(UPLOAD_FOLDERTEXT+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                logger.error("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return "下载附件失败，请检查文件“" + fileName + "”是否存在";
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return "下载附件失败,error:"+e.getMessage();
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
        }
        return null;
    }
}

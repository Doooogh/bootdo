package com.bootdo.front.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.filedetail.domain.FileDetailVO;
import com.bootdo.filedetail.service.FileDetailService;
import com.bootdo.filedir.domain.FileDirDO;
import com.bootdo.filedir.service.FileDirService;
import com.bootdo.student.domain.StudentDO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller()
@RequestMapping("/front/photo")
public class PhotoController {


    @Autowired
    private FileDetailService fileDetailService;

    @Autowired
    private FileDirService fileDirService;

    @Autowired
    BootdoConfig bootdoConfig;

    @GetMapping("/myPhoto")
    public String myPhoto(){
        return "/front/front/myphoto";
    }

    @GetMapping("/getPhoto")
    @ResponseBody
    public Map getPhoto(Integer pageNo, Long userId,Integer dirId){
        if(null==pageNo){
            pageNo=1;
        }
        if(null==userId){
            userId=ShiroUtils.getUserId();
        }
        Integer pageSize=10;
        Map<String,Object> map=new HashMap<>();
        String rc="0";
        Map<String,Object> params=new HashMap<>();
        try{
            //相册
            params.put("userId",userId);
            params.put("offset",(pageNo-1)*pageSize);  //待定
            params.put("limit",pageSize);  //查询10条
            params.put("dirId",dirId);  //查询相册
            List<FileDetailVO> list = fileDetailService.getList(params);
            map.put("photoList",list);
            map.put("total",fileDetailService.getListCount(params));
            map.put("pageNo",pageNo);
        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;
    }

    @GetMapping("/getDir")
    @ResponseBody
    public Map getDir(Long userId){
        Map<String,Object> map=new HashMap<>();
        String rc="0";
        if(null==userId){
            userId=ShiroUtils.getUserId();
        }
        try {
            map.put("createBy",userId);
            List<FileDirDO> list = fileDirService.list(map);
            map.put("dirList",list);
        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;
    }

    @RequestMapping("/download")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String url=request.getParameter("url");
        String fileName=UUID.randomUUID().toString().trim().replaceAll("-", "")+url.substring(url.lastIndexOf("."));
        String userAgent = request.getHeader("User-Agent");
        if (/* IE 8 至 IE 10 */
                userAgent.toUpperCase().contains("MSIE") ||
                        userAgent.contains("Trident/7.0")) {
            try {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (userAgent.toUpperCase().contains("MOZILLA") ||
                userAgent.toUpperCase().contains("CHROME")) {
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
        } else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        if (fileName != null) {
            //设置文件路径
            File file = new File(bootdoConfig.getUploadPath()+url.substring(url.lastIndexOf("/"),url.length()));
            if (file.exists()) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}

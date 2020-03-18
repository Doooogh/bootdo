package com.bootdo.front.controller;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.filedetail.domain.FileDetailVO;
import com.bootdo.filedetail.service.FileDetailService;
import com.bootdo.leavemsg.domain.LeavemsgDO;
import com.bootdo.leavemsg.service.LeavemsgService;
import com.bootdo.student.domain.StudentDO;
import com.bootdo.student.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页数据
 */
@RestController()
@RequestMapping("/front/index")
public class FrontController {

    @Autowired
    private FileDetailService fileDetailService;

    @Autowired
    private LeavemsgService leavemsgService;

    @Autowired
    private StudentService studentService;


    @GetMapping("/frontIndex")
    public Map frontIndex(){
        Map<String,Object> map=new HashMap<>();
        String rc="0";
        try{
            Long userId = ShiroUtils.getUserId();
            Map<String,Object> params=new HashMap<>();
            //最新照片
            params.put("userId",userId);
            params.put("sort","create_date");
            params.put("order","desc");
            params.put("offset",0);
            params.put("limit",3);  //查询3条
            List<FileDetailVO> photoList = fileDetailService.getList(params);
            params.clear();
            //最新留言
            params.put("touserId",userId);
            params.put("sort","create_date");
            params.put("order","desc");
            params.put("offset",0);
            params.put("limit",3);  //查询3条
            List<LeavemsgDO> leavemsgList = leavemsgService.list(params);
            params.clear();

            //访问记录 1 访问我
            //访问记录 2 我访问

            map.put("photoList",photoList);
            map.put("leavemsgList",leavemsgList);

        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;
    }

    @GetMapping("/getClaStu")
    public Map getClaStu(Integer pageNo){
        if(null==pageNo){
            pageNo=1;
        }
        Integer pageSize=10;
        Map<String,Object> map=new HashMap<>();
        String rc="0";
        Long userId = ShiroUtils.getUserId();
        Map<String,Object> params=new HashMap<>();
        try{
            //同班同学
            params.put("userId",userId);
            params.put("offset",(pageNo-1)*pageSize);  //待定
            params.put("limit",pageSize);  //查询3条
            List<StudentDO> studentList = studentService.list(params);
            map.put("studentList",studentList);
            map.put("total",studentService.count(params));
            map.put("pageNo",pageNo);
        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;
    }

}

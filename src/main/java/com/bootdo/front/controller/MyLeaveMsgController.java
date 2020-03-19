package com.bootdo.front.controller;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.leavemsg.domain.LeavemsgDO;
import com.bootdo.leavemsg.service.LeavemsgService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/front/leavemsg")
public class MyLeaveMsgController {

    @Autowired
    private LeavemsgService leavemsgService;

    @GetMapping("/myLeavemsg")
    public String myLeavemsg(){
        return "/front/front/leavemsg";
    }


    @GetMapping("/getList")
    @ResponseBody
    public Map<String,Object> list(Long userId,Integer pageNo){

        Map<String,Object> map=new HashMap<>();
        String rc="0";
        try{
            Map<String,Object> params=new HashMap<>();
            Integer pageSize=10;
            if(null==pageNo){
                pageNo=1;
            }
            if(null==userId){
                userId= ShiroUtils.getUserId();
            }
            params.put("touserId",userId);
            params.put("0","1");
            params.put("sort","create_date");
            params.put("order","desc");
            params.put("isPass",1);
            params.put("offset",(pageNo-1)*pageSize);
            params.put("limit",pageSize);
            List<LeavemsgDO> list = leavemsgService.list(params);
            int total=leavemsgService.count(params);
            map.put("leaveMsgList",list);
            map.put("total",total);
            map.put("pageNo",pageNo);
        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;
    }


    @PostMapping("/save")
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest request, HttpServletResponse response,Long touserId){
        String content = request.getParameterMap().get("content")[0];
        if(null==touserId){
            touserId=ShiroUtils.getUserId();
        }
        Map<String,Object> map=new HashMap<>();
        String rc="0";
        try{
            boolean hasZZ = leavemsgService.hasZZ(content);
            LeavemsgDO leavemsgDO=new LeavemsgDO();
            leavemsgDO.setCreateDate(new Date());
            leavemsgDO.setFromuserId((int)(long)ShiroUtils.getUserId());
            leavemsgDO.setContent(content);
            leavemsgDO.setTouserId((int)(long)touserId);
            if(hasZZ){
                leavemsgDO.setIsPass(0);
                rc="1";
            }else{
                leavemsgDO.setIsPass(1);
            }
            leavemsgService.save(leavemsgDO);
        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;
    }

    @PostMapping("/del")
    @ResponseBody
    public Map del(Integer id){
        Map<String,Object> map=new HashMap<>();
        String rc="0";
        try{
            int i = leavemsgService.remove(id);
            if(i<=0){
                rc="-1";
            }
        }catch (Exception e){
            e.printStackTrace();
            rc="-1";
        }
        map.put("rc",rc);
        return map;

    }


}

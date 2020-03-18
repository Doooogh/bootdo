package com.bootdo.front.controller;

import com.bootdo.leavemsg.service.LeavemsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/front/leavemsg")
public class MyLeaveMsgController {

    @Autowired
    private LeavemsgService leavemsgService;

    @GetMapping("/myLeavemsg")
    public String myLeavemsg(){
        return "/front/front/leavemsg";
    }
}

package com.hzu.competition_master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("system/index")
    public String index(){
        return "system/index";
    }
}

package com.example.sb_freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/page")
    public String show(Model model){
        model.addAttribute("name","小白");
        return "index";
    }
}

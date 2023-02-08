package com.blueland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
    public String adminHome(){
        return "admin/dashboard";
    }
      
    
    @RequestMapping(value = {"/admin/drill"}, method = RequestMethod.GET)
    public String adminDrill(){
        return "admin/drill";
    }
    
    @RequestMapping(value = {"/admin/jssone"}, method = RequestMethod.GET)
    public String jssone(){
        return "admin/jssone";
    }
    
    
    @RequestMapping(value = {"/admin/jsstwo"}, method = RequestMethod.GET)
    public String jsstwo(){
        return "admin/jsstwo";
    }
    
    @RequestMapping(value = {"/admin/jssthree"}, method = RequestMethod.GET)
    public String jssthree(){
        return "admin/jssthree";
    }
    
    @RequestMapping(value = {"/admin/ssone"}, method = RequestMethod.GET)
    public String ssone(){
        return "admin/ssone";
    }
    
    @RequestMapping(value = {"/admin/sstwo"}, method = RequestMethod.GET)
    public String sstwo(){
        return "admin/sstwo";
    }
    
    @RequestMapping(value = {"/admin/ssthree"}, method = RequestMethod.GET)
    public String ssthree(){
        return "admin/ssthree";
    }
}
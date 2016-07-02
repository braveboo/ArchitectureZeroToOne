package cn.mmb.b2b.user.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * *
 **/
@Controller
public class CompanyController {

    @RequestMapping("/addCompany")
    @RequiresPermissions("system:company:add")
    public ModelAndView addCompany(String a){
        String b = "abc";
        System.out.println(a==b);
        int c = 5/0;
        return new ModelAndView("success");
    }
}

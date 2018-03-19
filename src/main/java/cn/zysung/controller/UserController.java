package cn.zysung.controller;

import cn.zysung.service.UserService;
import com.alibaba.druid.support.json.JSONUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String hello(){
//        System.out.println(userService.selectTop10Thanks().get(1).toString());
//     return userService.selectTop10Thanks().toString();
        return "testEcharts";
    }
    @RequestMapping(value = "/zhihu",method = RequestMethod.GET)
    public String mainPage(Model model){
        model.addAttribute("userCount",userService.getUserCount());
        model.addAttribute("thanks",userService.selectTop10Thanks());
        model.addAllAttributes(userService.selectSexNums());
        model.addAttribute("eduTop10", userService.selectTop10Education());
        return "main";
    }


}

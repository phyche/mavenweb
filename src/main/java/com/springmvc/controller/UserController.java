package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.springmvc.entity.ResponseResult;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.log4j.Logger;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/index");
        System.out.println("**************");
        return view;
    }

    @RequestMapping("/test")
    @ResponseBody
    public User test(@RequestBody User user) {
        /*String msg="保存失败";
        int code=1;
        return JSON.toJSONString(ResponseResult.result(code, msg));*/

        /*Map map = new HashMap();
        map.put("status","1");
        return map;*/
        return user;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(String userName,String password,String pic) {

        String msg="保存失败";
        int code=1;
        User user = new User();
        user.setPic(pic);
        user.setPassword(password);
        user.setUsername(userName);
        userService.add(user);
        code=0;
        msg="保存成功";
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }

    @RequestMapping("/testRedis")
    @ResponseBody
    public String testRedis(String userName,String password,String pic) {
        String msg="保存失败";
        int code=1;
        User user = new User();
        user.setPic(pic);
        user.setPassword(password);
        user.setUsername(userName);
        redisTemplate.opsForValue().set("user","123");
        redisTemplate.opsForValue().set("userNew", user);
        String a = (String) redisTemplate.opsForValue().get("user");
        JSONObject b = JSONObject.fromObject(redisTemplate.opsForValue().get("userNew"));
        User user1 = (User) JSONObject.toBean(b,User.class);

        redisTemplate.opsForList().leftPush("userList",user);
        Long size = redisTemplate.opsForList().size("userList");
        JSONObject c = JSONObject.fromObject(redisTemplate.opsForList().rightPop("userList"));
        User user2 = (User) JSONObject.toBean(c,User.class);
        code=0;
        msg="保存成功";
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }

    @RequestMapping("/userManage")
    public ModelAndView userManage() {
        ModelAndView view = new ModelAndView("/user/list");
        return view;
    }

    @RequestMapping("/queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(String username,Integer pageNum,Integer pageSize) {

        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;
        Map<String,Object> page = userService.queryPage(username,pageNum,pageSize);
        return page;
    }
}

package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.springmvc.entity.ResponseResult;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class Handler {
    private Logger logger = Logger.getLogger(Handler.class);

}

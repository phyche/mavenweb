package com.springmvc.service.impl;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public void add(User user) {

        userDao.add(user);
    }

    @Override
    public Map<String, Object> queryPage(String username, Integer pageNum, Integer pageSize) {

        Integer pageStart = (pageNum - 1) * pageSize;
        List<User> userList = userDao.queryPage(username,pageStart,pageSize);
        int rowCount = userDao.queryPageCount(username);
        Map<String,Object> result = new HashMap<>();
        result.put("userList",userList);

        Integer pageCount = rowCount/pageSize;
        Map<String,Object> page = new HashMap<>();
        page.put("rowCount",rowCount);
        page.put("pageNum",pageNum);
        page.put("pageSize",pageSize);
        page.put("pageCount",pageCount);
        result.put("page",page);
        return result;
    }

}

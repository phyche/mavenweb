package com.springmvc.service;

import com.springmvc.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     *  Created by Vitelon on 2017-03-30
     *  新增
     * @param User
     */
    public void add(User User);

    public Map<String,Object> queryPage(String username,Integer pageNum,Integer pageSize);
}

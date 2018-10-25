package com.springmvc.dao;

import com.springmvc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository("userDao")
public interface UserDao {

    /**
     *  Created by Vitelon on 2017-03-30
     *  新增
     * @param User
     */
    public void add(User User);

    public List<User> queryPage(@Param("username")String username, @Param("pStart") Integer pStart,@Param("pSize") Integer pSize);

    public int queryPageCount(@Param("username")String username);
}

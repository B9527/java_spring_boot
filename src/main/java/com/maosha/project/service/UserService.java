package com.maosha.project.service;

import com.maosha.project.service.model.UserModel;

public interface UserService {
    //通过用户id获取对象的方法
    UserModel getUserById(Integer id);
}

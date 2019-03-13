package com.maosha.project.controller;

import com.maosha.project.controller.viewobject.UserVO;
import com.maosha.project.error.BusinessException;
import com.maosha.project.error.EmBusinessError;
import com.maosha.project.response.CommonReturnType;
import com.maosha.project.service.UserService;
import com.maosha.project.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException{
        // 调用service服务获取对应id的用户对象返回给前端

        UserModel userModel = userService.getUserById(id);
        if(userModel == null){
//            userModel.setEncrptPassword("111");
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }
    public UserVO convertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO  = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}

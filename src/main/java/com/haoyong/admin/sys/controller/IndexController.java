package com.haoyong.admin.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.haoyong.admin.common.pojo.Result;
import com.haoyong.admin.sys.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;
//
//    /**
//     * 根据token获取用户信息
//     */
    @GetMapping("info")
    public Result<Object> info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.success(userInfo);
    }
//
//    /**
//     * 获取菜单
//     * @return
//     */
    @GetMapping("menu")
    public Result<Object> getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Result.success(permissionList);
    }
//
    @PostMapping("logout")
    public Result<String> logout(){
        return Result.success("操作成功");
    }

}

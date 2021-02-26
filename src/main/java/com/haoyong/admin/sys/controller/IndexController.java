package com.haoyong.admin.sys.controller;



import com.alibaba.fastjson.JSONObject;
import com.haoyong.admin.common.ResultBody;

import com.haoyong.admin.sys.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     * SecurityContextHolder.getContext().getAuthentication().getAuthorities()   获取权限列表
     * SecurityContextHolder.getContext().getAuthentication().getCredentials()   jwt token凭证
     * SecurityContextHolder.getContext().getAuthentication().getPrincipal()    登录账号
     */
    @GetMapping("info")
    public ResultBody<Map<String, Object>> info(Authentication authentication){
        //获取当前登录用户用户名
        /**
         * principal   "admin"
         * credentials  token的值
         * authorities  权限列表
         */
        String username = authentication.getName();

        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return ResultBody.success(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */

    @GetMapping("menu")
    public ResultBody<List<JSONObject>> getMenu(Authentication authentication){
        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = authentication.getName();

        List<JSONObject> menu = indexService.getMenu(username);
        return ResultBody.success(menu);
    }

    @PostMapping("logout")
    public ResultBody<String> logout(){
        return ResultBody.success("退出成功");
    }

}

package com.haoyong.admin.sys.controller;



import com.haoyong.admin.annotation.MyLogAnno;
import com.haoyong.admin.common.pojo.Result;
import com.haoyong.admin.infrastructure.LogType;
import com.haoyong.admin.sys.service.IndexService;
import com.haoyong.admin.sys.vo.PermissionVo;
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
    public Result<Object> info(Authentication authentication){
        //获取当前登录用户用户名

        String username = authentication.getName();

        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.success(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */

    @GetMapping("menu")
    public Result<List<PermissionVo>> getMenu(Authentication authentication){
        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = authentication.getName();

        List<PermissionVo> menulist = indexService.getMenu(username);
        return Result.success(menulist);
    }

    @PostMapping("logout")
    public Result<String> logout(){
        return Result.success("操作成功");
    }

}

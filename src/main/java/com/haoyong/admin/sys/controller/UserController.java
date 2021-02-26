package com.haoyong.admin.sys.controller;



import com.haoyong.admin.Enum.DelStatus;
import com.haoyong.admin.common.ResultBody;
import com.haoyong.admin.sys.domain.User;

import com.haoyong.admin.sys.service.RoleService;
import com.haoyong.admin.sys.service.UserService;
import com.haoyong.admin.sys.dto.UserRolesDTO;
import com.haoyong.admin.sys.vo.UserVo;
import com.haoyong.admin.util.MD5Util;
import com.haoyong.admin.util.SnowflakeIdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/user")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    获取管理用户分页列表
    @GetMapping("{page}/{limit}")
    public ResultBody<Object> index(@PathVariable Integer page,
                                    @PathVariable Integer limit,
                                   @RequestParam("user_name") String user_name) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit);

        List<UserVo> list = userService.showUserList(user_name);


        PageImpl page1 = new PageImpl<UserVo>(list, pageRequest,list.size());
        return ResultBody.success(page1);

    }

//   新增管理用户
    @PostMapping("save")
    public ResultBody<Object> save(@RequestBody User user) {

        boolean b = userService.addUser(user);
        if (b) {
            return ResultBody.success();
        }
        return ResultBody.error("用户名已存在,请勿重复添加");
    }

    //  修改管理用户
    @PutMapping("update")
    public ResultBody<Object> updateById(@RequestBody User user) {
        boolean b = userService.updateUserInfo(user);
        if (b) {
            return ResultBody.success();
        }
        return ResultBody.error("用户名已存在,请勿重复添加");

    }

    //   删除管理用户
    @DeleteMapping("remove/{id}")
    public ResultBody<Object> remove(@PathVariable String id) {
        userService.remove(id);
        return ResultBody.success();
    }
//
//    @ApiOperation(value = "根据id列表删除管理用户")
//    @DeleteMapping("batchRemove")
//    public R batchRemove(@RequestBody List<String> idList) {
//        userService.removeByIds(idList);
//        return R.ok();
//    }
//
//    根据用户获取角色数据
    @GetMapping("/toAssign/{userId}")
    public ResultBody<Object> toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);

        return ResultBody.success(roleMap);
    }

//    根据用户分配角色
    @PostMapping("/doAssign")
    public ResultBody<Object> doAssign(@NotNull @RequestBody UserRolesDTO userRoleDTO) {
        roleService.saveUserRoleRealtionShip(userRoleDTO);
        return ResultBody.success();
    }
}


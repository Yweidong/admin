package com.haoyong.admin.sys.controller;


import com.haoyong.admin.Enum.CommonEnum;
import com.haoyong.admin.common.ResultBody;

import com.haoyong.admin.sys.domain.Permission;
import com.haoyong.admin.sys.dto.RolePermissionDTO;
import com.haoyong.admin.sys.service.PermissionService;
import com.haoyong.admin.sys.vo.PermissionVo;

import com.haoyong.admin.util.SnowflakeIdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController  {

    @Autowired
    private PermissionService permissionService;

    //根据角色获取菜单(树形结构)
    @GetMapping
//    @PreAuthorize("hasAnyRole('user.list')")
    public ResultBody<List<PermissionVo>> getAllMenu(Authentication authentication) {
        String userId = String.valueOf(authentication.getCredentials());
        List<PermissionVo> list =  permissionService.selectMenuTreeByUserId(userId);
        return ResultBody.success(list);
    }

    //删除菜单
    @DeleteMapping("/remove/{id}")
    public ResultBody<Object> delete(@PathVariable String id) {
        boolean delete = permissionService.delete(id);
        if (delete) {
            return ResultBody.success();
        }
        return ResultBody.error(CommonEnum.NOT_DEL_SUB);

    }

    /**
     *
     *根据角色获取菜单
     */
    @GetMapping("toAssign/{roleId}")
    public ResultBody<List<PermissionVo>> toAssign(@PathVariable String roleId) {
        List<PermissionVo> list = permissionService.selectAllMenu(roleId);
        return ResultBody.success(list);
    }

    /**
    * @Description:给角色分配权限

    * @return:
    * @Author: dong
    * @Date: 2020/12/9
    */

    @PostMapping("/doAssign")
    public ResultBody<Object> doAssign(@RequestBody RolePermissionDTO rolePermissionDTO) {
        String roleId = rolePermissionDTO.getRoleid();
        String permissionid = rolePermissionDTO.getPermissionid();
        permissionService.saveRolePermissionRealtionShip(roleId,permissionid);
        return ResultBody.success();
    }

    //新增菜单
    @PostMapping("save")
    public ResultBody<Object> save(@RequestBody Permission permission) {
        permission.setId(String.valueOf(SnowflakeIdWorkerUtil.getInstance().nextId()));
        boolean b = permissionService.addMenu(permission);
        if (b) {
            return ResultBody.success();
        }
        return ResultBody.error();
    }


    //修改菜单
    @PutMapping("update")
    public ResultBody<Object> updateById(@RequestBody Permission permission) {
        boolean b = permissionService.updateMenu(permission);
        if (b) return ResultBody.success();
        return ResultBody.error();

    }

}


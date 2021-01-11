package com.haoyong.admin.sys.controller;


import com.haoyong.admin.common.controller.CommonController;
import com.haoyong.admin.common.pojo.Result;
import com.haoyong.admin.sys.domain.Permission;
import com.haoyong.admin.sys.dto.RolePermissionDTO;
import com.haoyong.admin.sys.service.PermissionService;
import com.haoyong.admin.sys.vo.PermissionVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
//@CrossOrigin

public class PermissionController extends CommonController<PermissionVo, Permission,String> {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单

    @GetMapping
//    @PreAuthorize("hasAnyRole('user.list')")
    public Result<List<PermissionVo>> getAllMenu() {
        List<PermissionVo> list =  permissionService.queryAllMenu();
        return Result.success(list);
    }


    public Result<String> delete(@PathVariable String id) {
        return permissionService.delete(id);
    }

    /**
    * @Description:

    * @return:
    * @Author: dong
    * @Date: 2020/12/9
    */

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody RolePermissionDTO rolePermissionDTO) {
        String roleId = rolePermissionDTO.getRoleid();
        String permissionid = rolePermissionDTO.getPermissionid();
        permissionService.saveRolePermissionRealtionShip(roleId,permissionid);
        return Result.success("操作成功");
    }

//    @ApiOperation(value = "根据角色获取菜单")
//////    @GetMapping("toAssign/{roleId}")
//////    public R toAssign(@PathVariable String roleId) {
//////        List<Permission> list = permissionService.selectAllMenu(roleId);
//////        return R.ok().data("children", list);
//////    }



//    @ApiOperation(value = "新增菜单")
//    @PostMapping("save")
//    public R save(@RequestBody Permission permission) {
//        permissionService.save(permission);
//        return R.ok();
//    }

//    @ApiOperation(value = "修改菜单")
//    @PutMapping("update")
//    public R updateById(@RequestBody Permission permission) {
//        permissionService.updateById(permission);
//        return R.ok();
//    }

}


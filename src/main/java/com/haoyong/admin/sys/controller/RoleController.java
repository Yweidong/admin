package com.haoyong.admin.sys.controller;



import com.haoyong.admin.common.ResultBody;
import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.dto.RoleDTO;
import com.haoyong.admin.sys.service.RoleService;
import com.haoyong.admin.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 *  角色管理控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")

public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("{page}/{limit}")
    public ResultBody<Page<Role>> index(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            @RequestParam("role_name") String role_name) {

        //分页可以使用Pageable类，这个类底层通过构造方式创建对象，参数
        PageRequest pageRequest = PageRequest.of(page - 1, limit);
        Page<Role> rolePage = roleService.showRoleList(pageRequest, role_name);
        return ResultBody.success(rolePage);

//        System.out.println("查询出的总记录数: " + ret.getTotalElements());


    }
//
//    @ApiOperation(value = "获取角色")
//    @GetMapping("get/{id}")
//    public R get(@PathVariable String id) {
//        Role role = roleService.getById(id);
//        return R.ok().data("item", role);
//    }
//
//     新增角色
    @PostMapping("save")
    public ResultBody<Object> save( @Valid @RequestBody Role role) {
        boolean b = roleService.addRole(role);
        if (b) return ResultBody.success();
        return ResultBody.error();
    }
//
//    修改角色
    @PutMapping("update")
    public ResultBody<Object> updateById(@RequestBody Role role) {
        boolean b = roleService.updateRole(role);
        if (b) return ResultBody.success();
        return ResultBody.error();
    }

//    删除角色
    @DeleteMapping("remove/{id}")
    public ResultBody<Object> remove(@PathVariable String id) {
        boolean b = roleService.deleteRoleById(id);
        if (b) return ResultBody.success();
        return ResultBody.error();

    }
//
//    @ApiOperation(value = "根据id列表删除角色")
//    @DeleteMapping("batchRemove")
//    public R batchRemove(@RequestBody List<String> idList) {
//        roleService.removeByIds(idList);
//        return R.ok();
//    }
}


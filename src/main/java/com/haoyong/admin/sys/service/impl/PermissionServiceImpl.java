package com.haoyong.admin.sys.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.haoyong.admin.sys.domain.Permission;
import com.haoyong.admin.sys.domain.RolePermission;
import com.haoyong.admin.sys.domain.User;

import com.haoyong.admin.sys.helper.MenuHelper;
import com.haoyong.admin.sys.helper.PermissionHelper;
import com.haoyong.admin.sys.repository.SysPermissionRepository;
import com.haoyong.admin.sys.repository.SysRolePermissionRepository;
import com.haoyong.admin.sys.service.PermissionService;
import com.haoyong.admin.sys.service.UserService;
import com.haoyong.admin.sys.vo.PermissionVo;
import com.haoyong.admin.util.CopyUtil;
import com.haoyong.admin.util.SnowflakeIdWorkerUtil;
import com.haoyong.admin.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
@Slf4j
public class PermissionServiceImpl  implements PermissionService {

        @Autowired
        SysPermissionRepository sysPermissionRepository;
        @Autowired
        SysRolePermissionRepository sysRolePermissionRepository;

        @Autowired
        UserService userService;

    //获取全部菜单
    @Override
    public List<PermissionVo> queryAllMenu() {
        //排序字段

        List<Permission> permissionList = sysPermissionRepository.findAll();
        List<PermissionVo> permissionVos = CopyUtil.copyList(permissionList, PermissionVo.class);

        List<PermissionVo> result = PermissionHelper.getInstance().bulid(permissionVos);

        return result;
    }
    //根据角色获取菜单
    @Override
    public List<PermissionVo> selectAllMenu(String roleId) {
        List<Permission> permissions = sysPermissionRepository.findAll();
        List<RolePermission> rolePermissionList = sysRolePermissionRepository.findByRoleId(roleId);
        List<PermissionVo> permissionVos = CopyUtil.copyList(permissions, PermissionVo.class);
        for (int i = 0; i < permissionVos.size(); i++) {
            PermissionVo permissionVo = permissionVos.get(i);
            for (int i1 = 0; i1 < rolePermissionList.size(); i1++) {
                RolePermission rolePermission = rolePermissionList.get(i1);
                if (rolePermission.getPermissionId().equals(permissionVo.getId())) {
                    permissionVo.setSelect(true);
                }
            }
        }
        List<PermissionVo> permissionVoList = PermissionHelper.getInstance().bulid(permissionVos);
        return permissionVoList;
    }

    //给角色分配权限
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermissionRealtionShip(String roleId, String permissionIds) {
        List<RolePermission> ret = sysRolePermissionRepository.findByRoleId(roleId);
        //原来角色的权限
        List<String> collect = ret.stream().map(p -> p.getPermissionId()).collect(Collectors.toList());


        String[] split = permissionIds.split(",");
        List<String> asList = Arrays.asList(split);
        //比较差值
        //减少的
        List<String> collect1 = collect.stream().filter(item -> !asList.contains(item)).collect(Collectors.toList());

        if(!collect1.isEmpty()) {
            List<String> ids = ret.stream().filter(item -> collect1.contains(item.getPermissionId()))
                                            .map(p->p.getId())
                                            .collect(Collectors.toList());
            sysRolePermissionRepository.deleteByIdIn(ids);
        }
        //增加的
        List<String> collect2 = asList.stream().filter(item -> !collect.contains(item)).collect(Collectors.toList());

        if(!collect2.isEmpty()) {
            List<RolePermission> rolePermissionList = new ArrayList<>();
            for(String permissionId : collect2) {
                if(StringUtils.isEmpty(permissionId)) continue;

                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                rolePermission.setId(String.valueOf(SnowflakeIdWorkerUtil.getInstance().nextId()));
                rolePermissionList.add(rolePermission);
            }
            sysRolePermissionRepository.saveAll(rolePermissionList);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenu(Permission permission) {
        Permission permission1 = sysPermissionRepository.save(permission);

        if (permission1 != null) return true;
        return false;
    }

    //递归删除菜单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        List<Permission> permissionList = sysPermissionRepository.findByPid(id);
        if (!permissionList.isEmpty()) return false;
        sysPermissionRepository.deleteById(id);
        sysRolePermissionRepository.deleteByPermissionId(id);
        return true;

    }

    //    //根据用户id获取用户菜单
    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = sysPermissionRepository.findAllPermissionValue();
        } else {
            selectPermissionValueList = sysPermissionRepository.findPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }





    /**
     *
     *树形结构
     */
    @Override
    public List<PermissionVo> selectMenuTreeByUserId(String id) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(id)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = sysPermissionRepository.findAll();
        } else {
            selectPermissionList = sysPermissionRepository.findPermissionByUserId(id);
        }
        List<PermissionVo> permissionVos = CopyUtil.copyList(selectPermissionList, PermissionVo.class);
        List<PermissionVo> permissionVoList = PermissionHelper.getInstance().bulid(permissionVos);

        return permissionVoList;
    }

    /**
     *
     *后台左侧菜单栏
     */
    @Override
    public List<JSONObject> selectAdminMenu(String id) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(id)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = sysPermissionRepository.findAll();
        } else {
            selectPermissionList = sysPermissionRepository.findPermissionByUserId(id);
        }
        List<PermissionVo> permissionVos = CopyUtil.copyList(selectPermissionList, PermissionVo.class);
        List<PermissionVo> permissionVoList = PermissionHelper.getInstance().bulid(permissionVos);
        return MenuHelper.getInstance().bulid(permissionVoList);


    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        Optional<User> optional = userService.findById(userId);
        if(optional.isPresent() && "admin".equals(optional.get().getUsername())) {
           return true;
        }
        return false;
    }

    /**
     *	递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(String id, List<String> idList) {
        List<Permission> childList = sysPermissionRepository.findByPid(id);

        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMenu(Permission permission) {
        Permission permission1 = sysPermissionRepository.saveAndFlush(permission);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(String.valueOf(SnowflakeIdWorkerUtil.getInstance().nextId()));
        rolePermission.setRoleId("1");
        rolePermission.setPermissionId(permission.getId());
        RolePermission rolePermission1 = sysRolePermissionRepository.saveAndFlush(rolePermission);
        if (permission1!=null && rolePermission1 !=null) {
            return  true;
        }
        return false;

    }


}

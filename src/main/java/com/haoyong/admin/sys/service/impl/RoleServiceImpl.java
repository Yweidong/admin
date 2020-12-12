package com.haoyong.admin.sys.service.impl;


import com.haoyong.admin.common.service.impl.CommonServiceImpl;
import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.repository.SysRoleRepository;
import com.haoyong.admin.sys.service.RoleService;
import com.haoyong.admin.sys.service.UserRoleService;
import com.haoyong.admin.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class RoleServiceImpl extends CommonServiceImpl<RoleVo,Role,String> implements RoleService {

    @Autowired
    SysRoleRepository sysRoleRepository;


    @Autowired
    private UserRoleService userRoleService;
//
//
//    //根据用户获取角色数据
//    @Override
//    public Map<String, Object> findRoleByUserId(String userId) {
//        //查询所有的角色
//        List<Role> allRolesList =baseMapper.selectList(null);
//
//        //根据用户id，查询用户拥有的角色id
//        List<UserRole> existUserRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", userId).select("role_id"));
//
//        List<String> existRoleList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());
//
//        //对角色进行分类
//        List<Role> assignRoles = new ArrayList<Role>();
//        for (Role role : allRolesList) {
//            //已分配
//            if(existRoleList.contains(role.getId())) {
//                assignRoles.add(role);
//            }
//        }
//
//        Map<String, Object> roleMap = new HashMap<>();
//        roleMap.put("assignRoles", assignRoles);
//        roleMap.put("allRolesList", allRolesList);
//        return roleMap;
//    }
//
//    //根据用户分配角色
//    @Override
//    public void saveUserRoleRealtionShip(String userId, String[] roleIds) {
//        userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", userId));
//
//        List<UserRole> userRoleList = new ArrayList<>();
//        for(String roleId : roleIds) {
//            if(StringUtils.isEmpty(roleId)) continue;
//            UserRole userRole = new UserRole();
//            userRole.setUserId(userId);
//            userRole.setRoleId(roleId);
//
//            userRoleList.add(userRole);
//        }
//        userRoleService.saveBatch(userRoleList);
//    }
//
    @Override
    public List<Role> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<String> userRoleList = userRoleService.getUserRole(id);

        List<Role> roleList = new ArrayList<>();
        if(userRoleList.size() > 0) {
            roleList = sysRoleRepository.findByIdIn(userRoleList);
        }
        return roleList;
    }
}

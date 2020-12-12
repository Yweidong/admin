package com.haoyong.admin.sys.service;


import com.haoyong.admin.common.service.CommonService;
import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface RoleService extends CommonService<RoleVo,Role,String> {

//    //根据用户获取角色数据
//    Map<String, Object> findRoleByUserId(String userId);
//
//    //根据用户分配角色
//    void saveUserRoleRealtionShip(String userId, String[] roleId);
//
    List<Role> selectRoleByUserId(String id);
}

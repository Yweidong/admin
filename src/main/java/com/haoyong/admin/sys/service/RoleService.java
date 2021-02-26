package com.haoyong.admin.sys.service;



import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.dto.RoleDTO;
import com.haoyong.admin.sys.dto.UserRolesDTO;
import com.haoyong.admin.sys.vo.RoleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
public interface RoleService {

    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(String userId);

//    //根据用户分配角色
    void saveUserRoleRealtionShip(UserRolesDTO userRoleDTO);
//
    List<Role> selectRoleByUserId(String id);

    //角色列表
    Page<Role> showRoleList(PageRequest pageRequest,String role_name);

    //新增角色
    boolean addRole(Role role);

    //修改角色
    boolean updateRole(Role role);
    //删除角色
    boolean deleteRoleById(String id);
}

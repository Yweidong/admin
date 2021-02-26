package com.haoyong.admin.sys.service;


import com.alibaba.fastjson.JSONObject;
import com.haoyong.admin.sys.domain.Permission;
import com.haoyong.admin.sys.vo.PermissionVo;


import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionService  {

    //获取全部菜单
    List<PermissionVo> queryAllMenu();

//    //根据角色获取菜单
    List<PermissionVo> selectAllMenu(String roleId);
//
//    //给角色分配权限
    void saveRolePermissionRealtionShip(String roleId, String permissionId);

    //添加菜单功能
    boolean addMenu(Permission permission);

    //修改菜单

    boolean updateMenu(Permission permission);

   //删除菜单
    boolean delete(String id);
//
//    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);



//    树形结构
    List<PermissionVo> selectMenuTreeByUserId(String id);
    //后台菜单栏结构
    List<JSONObject> selectAdminMenu(String id);
}

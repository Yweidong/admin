package com.haoyong.admin.sys.repository;


import com.haoyong.admin.sys.domain.Permission;
import com.haoyong.admin.sys.domain.RolePermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:29
 **/
@Repository
public interface SysRolePermissionRepository extends JpaRepository<RolePermission,String>, JpaSpecificationExecutor<RolePermission> {
    List<RolePermission> findByRoleId(String roleId);

    void deleteByPermissionId(String permissionId);
    void deleteByIdIn(List<String> ids);


}

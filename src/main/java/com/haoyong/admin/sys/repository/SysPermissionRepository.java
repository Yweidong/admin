package com.haoyong.admin.sys.repository;


import com.haoyong.admin.sys.domain.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface SysPermissionRepository extends JpaRepository<Permission,String>, JpaSpecificationExecutor<Permission> {
    List<Permission> findByPid(String id);

    @Query(value = "select p.permission_value from sys_permission p where p.type = 2 ",nativeQuery = true)
    List<String> findAllPermissionValue();

    @Query(value = "select p.permission_value from sys_user_role ur inner join sys_role_permission rp on rp.role_id = ur.role_id inner join sys_permission p on p.id = rp.permission_id where ur.user_id = :userid and p.type = 2 ",nativeQuery = true)
    List<String> findPermissionValueByUserId(@Param("userid") String userid);

    @Query(value = "select * from sys_user_role ur inner join sys_role_permission rp on rp.role_id = ur.role_id inner join sys_permission p on p.id = rp.permission_id where ur.user_id = :userid and p.type = 2 ",nativeQuery = true)
    List<Permission> findPermissionByUserId(@Param("userid") String userid);

}

package com.haoyong.admin.sys.repository;

import com.haoyong.admin.common.repository.CommonRepository;
import com.haoyong.admin.sys.domain.Permission;

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
public interface SysPermissionRepository extends CommonRepository<Permission, String> {
    List<Permission> findByPid(String id);





}

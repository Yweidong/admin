package com.haoyong.admin.sys.repository;


import com.haoyong.admin.sys.domain.Role;

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
public interface SysRoleRepository extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {
    List<Role> findByIdIn(List<String> ids);
}

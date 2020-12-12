package com.haoyong.admin.sys.repository;

import com.haoyong.admin.common.repository.CommonRepository;
import com.haoyong.admin.sys.domain.UserRole;

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
public interface SysUserRoleRepository extends CommonRepository<UserRole, String> {

    @Query(value = "select sur.role_id from sys_user_role sur where sur.user_id = :userid",nativeQuery = true)
    List<String> findByUserId(@Param("userid") String userid);
}

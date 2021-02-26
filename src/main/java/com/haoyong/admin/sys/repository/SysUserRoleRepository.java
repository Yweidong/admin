package com.haoyong.admin.sys.repository;



import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.domain.UserRole;
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
public interface SysUserRoleRepository extends JpaRepository<UserRole,String>, JpaSpecificationExecutor<UserRole> {

    @Query(value = "select sur.role_id from sys_user_role sur where sur.user_id = :userid",nativeQuery = true)
    List<String> findByUserIdNew(@Param("userid") String userid);

    void deleteByRoleId(String roleid);

    void deleteByIdIn(List<String> ids);

    List<UserRole> findByUserId(String user_id);
}

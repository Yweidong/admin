package com.haoyong.admin.sys.repository;



import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:29
 **/
@Repository
public interface SysUserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    @Query(value =
            "select sys_user.id,sys_user.username ,temp.role_id,temp.role_name from sys_user left join (select sys_user_role.role_id,sys_user_role.user_id,sys_role.role_name from" +
                    " sys_user_role left join sys_role on sys_user_role.role_id = sys_role.id) as temp on temp.user_id = sys_user.id " +
                    "where sys_user.is_deleted = 'N' and if(:username != '',sys_user.username like %:username%,1=1)",nativeQuery = true)
    List<Map<String,Object>> findUserList(@Param("username") String username);

    User findByUsernameAndIdNot(String username,String id);
}

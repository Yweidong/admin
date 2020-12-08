package com.haoyong.admin.sys.repository;

import com.haoyong.admin.common.repository.CommonRepository;
import com.haoyong.admin.sys.entity.SysRole;
import com.haoyong.admin.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:29
 **/
@Repository
public interface SysRoleRepository extends CommonRepository<SysRole, String> {
}

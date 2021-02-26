package com.haoyong.admin.sys.service.impl;


import com.haoyong.admin.sys.repository.SysUserRoleRepository;
import com.haoyong.admin.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    SysUserRoleRepository sysUserRoleRepository;

    @Override
    public List<String> getUserRole(String userid) {
       return sysUserRoleRepository.findByUserIdNew(userid);
    }
}

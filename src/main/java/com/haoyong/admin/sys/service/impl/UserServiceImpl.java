package com.haoyong.admin.sys.service.impl;


import com.haoyong.admin.common.service.impl.CommonServiceImpl;
import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.repository.SysUserRepository;
import com.haoyong.admin.sys.service.UserService;
import com.haoyong.admin.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends CommonServiceImpl<UserVo, User,String> implements UserService {
    @Autowired
    SysUserRepository sysUserRepository;
    @Override
    public User selectByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(String id) {
        return sysUserRepository.findById(id);
    }
}

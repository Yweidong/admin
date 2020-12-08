package com.haoyong.admin.sys.service.impl;

import com.haoyong.admin.common.service.impl.CommonServiceImpl;
import com.haoyong.admin.sys.entity.SysUser;
import com.haoyong.admin.sys.repository.SysUserRepository;
import com.haoyong.admin.sys.service.SysUserService;
import com.haoyong.admin.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:57
 **/
@Service
public class SysUserServiceImpl extends CommonServiceImpl<SysUserVo, SysUser, String> implements SysUserService {

    @Autowired
    SysUserRepository sysUserRepository;
    @Override
    public SysUserVo getByUserName(String username) {
       return sysUserRepository.findByUsername(username);
    }
}

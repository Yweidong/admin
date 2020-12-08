package com.haoyong.admin.secrity.service;

import cn.hutool.core.util.ObjectUtil;
import com.haoyong.admin.sys.service.SysUserService;
import com.haoyong.admin.sys.vo.SysUserVo;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:51
 **/
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserVo byUserName = sysUserService.getByUserName(username);
        if (ObjectUtil.isNull(byUserName)) {
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        log.info(byUserName.getRoles().toString());
//        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        return null;
    }
}

package com.haoyong.admin.secrity.service;


import com.haoyong.admin.Enum.CommonEnum;

import com.haoyong.admin.exception.BizException;

import com.haoyong.admin.secrity.entity.SecurityUser;


import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.service.PermissionService;
import com.haoyong.admin.sys.service.UserService;
import com.haoyong.admin.sys.vo.UserVo;
import com.haoyong.admin.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user){
            throw new BizException(CommonEnum.BODY_NOT_MATCH.getResultCode(),"用户名不存在");

        }
        // 返回UserDetails实现类
        com.haoyong.admin.secrity.entity.User curUser = new com.haoyong.admin.secrity.entity.User();
        BeanUtils.copyProperties(user,curUser);
//        CopyUtil.copy(user,curUser.getClass());
        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}

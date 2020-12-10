package com.haoyong.admin.sys.service;


import com.haoyong.admin.common.service.CommonService;
import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.vo.UserVo;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService extends CommonService<UserVo, User,String> {

//     从数据库中取出用户信息
    User selectByUsername(String username);

    Optional<User> findById(String id);

}

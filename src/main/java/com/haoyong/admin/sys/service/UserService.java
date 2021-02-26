package com.haoyong.admin.sys.service;



import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.dto.UserDTO;
import com.haoyong.admin.sys.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService  {

//     从数据库中取出用户信息
    User selectByUsername(String username);

    Optional<User> findById(String id);

    //用户管理查看
    List<UserVo> showUserList( String user_name);
    //新增用户
    boolean addUser(User user);

    //删除用户
    void remove(String id);
    //修改用户
    boolean updateUserInfo(User user);
}

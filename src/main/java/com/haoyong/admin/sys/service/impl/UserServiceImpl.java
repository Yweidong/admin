package com.haoyong.admin.sys.service.impl;



import com.haoyong.admin.Enum.DelStatus;
import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.dto.UserDTO;
import com.haoyong.admin.sys.repository.SysUserRepository;
import com.haoyong.admin.sys.service.UserService;
import com.haoyong.admin.sys.vo.UserVo;
import com.haoyong.admin.util.MD5Util;
import com.haoyong.admin.util.SnowflakeIdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class UserServiceImpl  implements UserService {
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

    @Override
    public List<UserVo> showUserList(String user_name) {

        List<Map<String, Object>> userList = sysUserRepository.findUserList(user_name);
        List<UserVo> list = new ArrayList<>();
        userList.forEach(stringObjectMap -> {
            for (UserVo userVo : list) {
                if (stringObjectMap.get("username").equals(userVo.getUsername())) {
                    userVo.getRoles().add((String)stringObjectMap.get("role_name"));
                    return;
                }
            }

            UserVo userVo = new UserVo();
            userVo.setId((String) stringObjectMap.get("id"));
            userVo.setUsername((String) stringObjectMap.get("username"));
            List<String> list1 = new ArrayList<>();
            if ( stringObjectMap.get("role_name")!=null) {
                list1.add((String) stringObjectMap.get("role_name"));
            }

            userVo.setRoles(list1);
            list.add(userVo);
        });

    return list;
    }


    //新增用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(User user) {
        User user1 = sysUserRepository.findByUsername(user.getUsername());
        if (user1 != null) return false;
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        user.setId(String.valueOf(SnowflakeIdWorkerUtil.getInstance().nextId()));
        user.setIsDeleted(DelStatus.N);

        sysUserRepository.save(user);

        return true;
    }

    //删除用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        Optional<User> user = sysUserRepository.findById(id);
        if (user.isPresent()) {
            user.get().setIsDeleted(DelStatus.Y);
        }
    }

    //修改用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserInfo(User user) {

        User u = sysUserRepository.findByUsernameAndIdNot(user.getUsername(),user.getId());
        if (u!=null) {
            return false;
        }

        Optional<User> user1 = sysUserRepository.findById(user.getId());
        user1.get().setUsername(user.getUsername());
//        user.setPassword(MD5Util.encrypt(user.getPassword()));
        sysUserRepository.save(user1.get());
        return true;
    }
}

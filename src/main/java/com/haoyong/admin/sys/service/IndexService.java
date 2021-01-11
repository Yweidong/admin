package com.haoyong.admin.sys.service;

import com.haoyong.admin.sys.vo.PermissionVo;

import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单
     * @param username
     * @return
     */
    List<PermissionVo> getMenu(String username);

}

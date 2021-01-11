package com.haoyong.admin.infrastructure;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-16 14:33
 **/
public interface RedisKey {
     String ROLE_AUTH_LIST = "role_auth_list:";//账号对应的角色列表
    String USER_LOGIN_TOKEN = "user_login_token:";
}

package com.haoyong.admin.sys.service;

import com.haoyong.admin.common.service.CommonService;
import com.haoyong.admin.sys.entity.SysUser;
import com.haoyong.admin.sys.vo.SysUserVo;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:53
 **/
public interface SysUserService extends CommonService<SysUserVo, SysUser, String> {
    SysUserVo getByUserName(String username);
}

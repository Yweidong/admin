package com.haoyong.admin.sys.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-10 09:49
 **/
@Data
public class UserVo {
    private String id;
    private String username;
    private String nickName;
    private String salt;
    private String token;
    private Date gmtCreate;
    private Date gmtModified;
}

package com.haoyong.admin.sys.vo;

import com.haoyong.admin.sys.domain.Role;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private List roles;
//    private Date gmtCreate;
//    private Date gmtModified;


}

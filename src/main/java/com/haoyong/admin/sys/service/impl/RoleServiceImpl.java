package com.haoyong.admin.sys.service.impl;



import com.haoyong.admin.sys.domain.Role;
import com.haoyong.admin.sys.domain.UserRole;
import com.haoyong.admin.sys.dto.RoleDTO;
import com.haoyong.admin.sys.dto.UserRolesDTO;
import com.haoyong.admin.sys.repository.SysRolePermissionRepository;
import com.haoyong.admin.sys.repository.SysRoleRepository;
import com.haoyong.admin.sys.repository.SysUserRoleRepository;
import com.haoyong.admin.sys.service.RoleService;
import com.haoyong.admin.sys.service.UserRoleService;
import com.haoyong.admin.sys.vo.RoleVo;
import com.haoyong.admin.util.SnowflakeIdWorkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
@Slf4j
public class RoleServiceImpl  implements RoleService {

    @Autowired
    SysRoleRepository sysRoleRepository;

    @Autowired
    SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    UserRoleService userRoleService;



    @Override
    public Page<Role> showRoleList(PageRequest pageRequest, String role_name) {
        //条件拼接可以使用Specification,可以扩展它的内部方法
        Page<Role> ret = sysRoleRepository.findAll((Specification<Role>) (root, criteriaQuery, criteriaBuilder) -> {

            //Predicate类的集合可以进行and拼接
            List<Predicate> conditions = Lists.newArrayList();
            if (!StringUtils.isEmpty(role_name)) {
                conditions.add( criteriaBuilder.like(root.get("roleName"), "%" + role_name + "%"));
            }

            return criteriaBuilder.and(conditions.toArray(new Predicate[conditions.size()]));
        }, pageRequest);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(Role role) {
        role.setId(String.valueOf(SnowflakeIdWorkerUtil.getInstance().nextId()));
        Role role1 = sysRoleRepository.saveAndFlush(role);
        if (role1 != null) return true;

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(Role role) {
        Role role1 = sysRoleRepository.save(role);
        if (role1 != null) return true;

        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoleById(String id) {

        sysRoleRepository.deleteById(id);
        sysUserRoleRepository.deleteByRoleId(id);
        return true;
    }



//    //根据用户获取角色数据
    @Override
    public Map<String, Object> findRoleByUserId(String userId) {


        List<Role> allRolesList = sysRoleRepository.findAll();
        //根据用户id，查询用户拥有的角色id
        List<String> byUserId = sysUserRoleRepository.findByUserIdNew(userId);

       //对角色进行分类
        List<Role> assignRoles = sysRoleRepository.findByIdIn(byUserId);


        for (Role role : allRolesList) {
            //已分配
            if(assignRoles.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;

    }

    //根据用户分配角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRoleRealtionShip(UserRolesDTO userRoleDTO) {
        //查找该用户原来的角色id集合

        List<UserRole> userRoles = sysUserRoleRepository.findByUserId(userRoleDTO.getUserid());

        //原来角色的权限
        List<String> collect = userRoles.stream().map(p -> p.getRoleId()).collect(Collectors.toList());
        //将新传过来的角色id以逗号的格式拆分
        String[] split = userRoleDTO.getRoleids().split(",");
        List<String> asList = Arrays.asList(split);

        //比较两个id集合
        //差值
        List<String> collect1 = collect.stream()
                .filter(item -> !asList.contains(item))
                .filter(item-> !item.isEmpty())
                .collect(Collectors.toList());

        if(!collect1.isEmpty()) {
            List<String> ids = userRoles.stream().filter(item -> collect1.contains(item.getRoleId()))
                    .map(p->p.getId())
                    .collect(Collectors.toList());
            sysUserRoleRepository.deleteByIdIn(ids);
        }

        //增加的
        List<String> collect2 = asList.stream()
                .filter(item -> !collect.contains(item))
                .filter(item-> !item.isEmpty())
                .collect(Collectors.toList());

        if (!collect2.isEmpty()) {
            List<UserRole> lists = new ArrayList<>();
            collect2.forEach(s -> {
                UserRole userRole = new UserRole();
                userRole.setId(String.valueOf(SnowflakeIdWorkerUtil.getInstance().nextId()));
                userRole.setUserId(userRoleDTO.getUserid());
                userRole.setRoleId(s);
                lists.add(userRole);
            });
            sysUserRoleRepository.saveAll(lists);
        }
    }

    @Override
    public List<Role> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<String> userRoleList = userRoleService.getUserRole(id);

        List<Role> roleList = new ArrayList<>();
        if(userRoleList.size() > 0) {
            roleList = sysRoleRepository.findByIdIn(userRoleList);
        }
        return roleList;
    }
}

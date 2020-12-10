package com.haoyong.admin.sys.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.haoyong.admin.common.service.impl.CommonServiceImpl;
import com.haoyong.admin.sys.domain.Permission;
import com.haoyong.admin.sys.domain.RolePermission;
import com.haoyong.admin.sys.domain.User;
import com.haoyong.admin.sys.repository.SysPermissionRepository;
import com.haoyong.admin.sys.repository.SysRolePermissionRepository;
import com.haoyong.admin.sys.service.PermissionService;
import com.haoyong.admin.sys.service.UserService;
import com.haoyong.admin.sys.vo.PermissionVo;
import com.haoyong.admin.util.CopyUtil;
import com.haoyong.admin.util.MD5Util;
import com.haoyong.admin.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
@Slf4j
public class PermissionServiceImpl extends CommonServiceImpl<PermissionVo, Permission,String> implements PermissionService {

        @Autowired
        SysPermissionRepository sysPermissionRepository;
        @Autowired
        SysRolePermissionRepository sysRolePermissionRepository;

        @Autowired
        UserService userService;

    //获取全部菜单
    @Override
    public List<PermissionVo> queryAllMenu() {
        //排序字段

        List<Permission> permissionList = sysPermissionRepository.findAll();
        List<PermissionVo> permissionVos = CopyUtil.copyList(permissionList, PermissionVo.class);

        List<PermissionVo> result = bulid(permissionVos);

        return result;
    }

    //根据角色获取菜单
//    @Override
//    public List<Permission> selectAllMenu(String roleId) {
//        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));
//
//        //根据角色id获取角色权限
//        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id",roleId));
//        //转换给角色id与角色权限对应Map对象
////        List<String> permissionIdList = rolePermissionList.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());
////        allPermissionList.forEach(permission -> {
////            if(permissionIdList.contains(permission.getId())) {
////                permission.setSelect(true);
////            } else {
////                permission.setSelect(false);
////            }
////        });
//        for (int i = 0; i < allPermissionList.size(); i++) {
//            Permission permission = allPermissionList.get(i);
//            for (int m = 0; m < rolePermissionList.size(); m++) {
//                RolePermission rolePermission = rolePermissionList.get(m);
//                if(rolePermission.getPermissionId().equals(permission.getId())) {
//                    permission.setSelect(true);
//                }
//            }
//        }
//
//
//        List<Permission> permissionList = bulid(allPermissionList);
//        return permissionList;
//    }

    //给角色分配权限
    @Override
    @Transactional
    public void saveRolePermissionRealtionShip(String roleId, String permissionIds) {
        List<RolePermission> ret = sysRolePermissionRepository.findByRoleId(roleId);
        //原来角色的权限
        List<String> collect = ret.stream().map(p -> p.getPermissionId()).collect(Collectors.toList());


        String[] split = permissionIds.split(",");
        List<String> asList = Arrays.asList(split);
        //比较差值
        //减少的
        List<String> collect1 = collect.stream().filter(item -> !asList.contains(item)).collect(Collectors.toList());

        if(!collect1.isEmpty()) {
            List<String> ids = ret.stream().filter(item -> collect1.contains(item.getPermissionId()))
                                            .map(p->p.getId())
                                            .collect(Collectors.toList());
            sysRolePermissionRepository.deleteByIdIn(ids);
        }
        //增加的
        List<String> collect2 = asList.stream().filter(item -> !collect.contains(item)).collect(Collectors.toList());

        if(!collect2.isEmpty()) {
            List<RolePermission> rolePermissionList = new ArrayList<>();
            for(String permissionId : collect2) {
                if(StringUtils.isEmpty(permissionId)) continue;

                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                rolePermission.setId(UUIDUtil.getUUID());
                rolePermissionList.add(rolePermission);
            }
            sysRolePermissionRepository.saveAll(rolePermissionList);
        }

    }

    //递归删除菜单
    @Override
    @Transactional
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        this.selectChildListById(id, idList);

        idList.add(id);
        sysPermissionRepository.deleteByIdIn(idList);
    }

//    //根据用户id获取用户菜单
    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = sysPermissionRepository.findAllPermissionValue();
        } else {
            selectPermissionValueList = sysPermissionRepository.findPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

//    @Override
//    public List<JSONObject> selectPermissionByUserId(String userId) {
//        List<Permission> selectPermissionList = null;
//        if(this.isSysAdmin(userId)) {
//            //如果是超级管理员，获取所有菜单
//            selectPermissionList = baseMapper.selectList(null);
//        } else {
//            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
//        }
//
//        List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
//        List<JSONObject> result = MemuHelper.bulid(permissionList);
//        return result;
//    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        Optional<User> optional = userService.findById(userId);
        if(optional.isPresent() && "admin".equals(optional.get().getUsername())) {
           return true;
        }
        return false;
    }

    /**
     *	递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(String id, List<String> idList) {
        List<Permission> childList = sysPermissionRepository.findByPid(id);

        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    private static List<PermissionVo> bulid(List<PermissionVo> treeNodes) {
        List<PermissionVo> trees = new ArrayList<>();
        for (PermissionVo treeNode : treeNodes) {

            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static PermissionVo findChildren(PermissionVo treeNode,List<PermissionVo> treeNodes) {
        treeNode.setChildren(new ArrayList<PermissionVo>());

        for (PermissionVo it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

//
//    private static Permission selectChildren(Permission permissionNode, List<Permission> permissionList) {
//        //1 因为向一层菜单里面放二层菜单，二层里面还要放三层，把对象初始化
//        permissionNode.setChildren(new ArrayList<Permission>());
//
//        //2 遍历所有菜单list集合，进行判断比较，比较id和pid值是否相同
//        for(Permission it : permissionList) {
//            //判断 id和pid值是否相同
//            if(permissionNode.getId().equals(it.getPid())) {
//                //把父菜单的level值+1
//                int level = permissionNode.getLevel()+1;
//                it.setLevel(level);
//                //如果children为空，进行初始化操作
//                if(permissionNode.getChildren() == null) {
//                    permissionNode.setChildren(new ArrayList<Permission>());
//                }
//                //把查询出来的子菜单放到父菜单里面
//                permissionNode.getChildren().add(selectChildren(it,permissionList));
//            }
//        }
//        return permissionNode;
//    }
//

//
//    //2 根据当前菜单id，查询菜单里面子菜单id，封装到list集合
//    private void selectPermissionChildById(String id, List<String> idList) {
//        //查询菜单里面子菜单id
//        QueryWrapper<Permission>  wrapper = new QueryWrapper<>();
//        wrapper.eq("pid",id);
//        wrapper.select("id");
//        List<Permission> childIdList = baseMapper.selectList(wrapper);
//        //把childIdList里面菜单id值获取出来，封装idList里面，做递归查询
//        childIdList.stream().forEach(item -> {
//            //封装idList里面
//            idList.add(item.getId());
//            //递归查询
//            this.selectPermissionChildById(item.getId(),idList);
//        });
//    }
//

}

package com.haoyong.admin.sys.helper;

import com.haoyong.admin.sys.vo.PermissionVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-11 16:10
 **/
public class PermissionHelper {
    final static PermissionHelper INSTANCE = new PermissionHelper();
    public static PermissionHelper getInstance() {
        return INSTANCE;
    }


    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public  List<PermissionVo> bulid(List<PermissionVo> treeNodes) {
        List<PermissionVo> trees = new ArrayList<>();
        for (PermissionVo treeNode : treeNodes) {

            if ("0".equals(treeNode.getPid())) {

                Map<String,Object> map = new HashMap<>();

                map.put("title",treeNode.getName());
                map.put("icon",treeNode.getIcon());
                treeNode.setMeta(map);

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


                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }

                treeNode.getChildren().add(findChildren(it,treeNodes));


            }
            Map<String,Object> map = new HashMap<>();

            map.put("title",treeNode.getName());
            map.put("icon",treeNode.getIcon());
            treeNode.setMeta(map);

        }
        return treeNode;
    }
}

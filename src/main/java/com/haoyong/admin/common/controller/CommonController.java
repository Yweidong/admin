package com.haoyong.admin.common.controller;

import com.haoyong.admin.common.pojo.PageInfo;
import com.haoyong.admin.common.pojo.Result;
import com.haoyong.admin.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
* @Description:
* @Param:  <V> 实体类VO
 * @Param:  <E>  实体类
*  @Param:  <>  id主键类型
* @Author: dong
* @Date: 2020/12/5
*/
public class CommonController<V,E,T> {
    @Autowired
    private CommonService<V, E, T> commonService;

    @GetMapping("get/{id}")
    public Result<V> get(@PathVariable("id") T id) {
        return commonService.get(id);
    }

    @DeleteMapping("delete/{id}")
    public Result<T> delete( @PathVariable("id") T id) {
        return commonService.delete(id);
    }

    @PostMapping("save")
    public Result<V> save(V entityVo) {
        return commonService.save(entityVo);
    }

    @GetMapping("list")
    public Result<List<V>> list(V entityVo) {
        return commonService.list(entityVo);
    }

    /*
        CRUD、分页、排序测试
     */
    @GetMapping("page")
    public Result<PageInfo<V>> page(V entityVo) {
        return commonService.page(entityVo);
    }

}

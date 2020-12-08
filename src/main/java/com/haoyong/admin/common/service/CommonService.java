package com.haoyong.admin.common.service;

import com.haoyong.admin.common.pojo.PageInfo;
import com.haoyong.admin.common.pojo.Result;

import java.util.List;

/**
 * @Description:
 * @Param:  <V> 实体类VO
 * @Param:  <E>  实体类
 *  @Param:  <>  id主键类型
 * @Author: dong
 * @Date: 2020/12/5
 */
public interface CommonService<V,E,T> {
    Result<PageInfo<V>> page(V entityVo);

    Result<List<V>> list(V entityVo);

    Result<V> get(T id);

    Result<V> save(V entityVo);

    Result<T> delete(T id);
}

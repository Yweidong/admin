package com.haoyong.admin.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * 通用Repository
 *
 * @param <E> 实体类
 * @param <T> id主键类型
 */
@NoRepositoryBean
public interface CommonRepository<E,T> extends JpaRepository<E,T>, JpaSpecificationExecutor<E> {
    void deleteByIdIn(List<String> ids);
}

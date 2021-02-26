package com.haoyong.admin.log.repository;

import com.haoyong.admin.log.domain.OperLog;
import com.haoyong.admin.sys.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: admin
 * @description: 操作日志
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-21 11:00
 **/
@Repository
public interface SysOperLogRepository extends JpaRepository<OperLog,String>, JpaSpecificationExecutor<OperLog> {
}

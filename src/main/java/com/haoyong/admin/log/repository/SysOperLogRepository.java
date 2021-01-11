package com.haoyong.admin.log.repository;

import com.haoyong.admin.common.repository.CommonRepository;
import com.haoyong.admin.log.domain.OperLog;
import org.springframework.stereotype.Repository;

/**
 * @program: admin
 * @description: 操作日志
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-21 11:00
 **/
@Repository
public interface SysOperLogRepository extends CommonRepository<OperLog,String> {
}

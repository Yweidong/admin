package com.haoyong.admin.log.repository;

import com.haoyong.admin.common.repository.CommonRepository;
import com.haoyong.admin.log.domain.ExcLog;
import org.springframework.stereotype.Repository;

/**
 * @program: admin
 * @description: 异常日志
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-21 11:00
 **/
@Repository
public interface SysExcLogRepository extends CommonRepository<ExcLog,String> {
}

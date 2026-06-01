package com.example.demo.service;

import com.example.demo.entity.SysLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: zhoujing
 * @Date: 2023/11/20 14:05
 * @Description:
 */
@Slf4j
@Service
public class SysLogService {

    public boolean save(SysLogEntity sysLog) {
        log.info("sysLog: {}", sysLog.toString());
        return true;
    }
}

package com.example.demo.service.engine;

import com.example.demo.model.aggregates.TreeRich;
import com.example.demo.model.vo.EngineResult;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 14:44
 * @Description:
 */
public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);
}

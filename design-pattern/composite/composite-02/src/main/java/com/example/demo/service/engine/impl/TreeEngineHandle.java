package com.example.demo.service.engine.impl;

import com.example.demo.model.aggregates.TreeRich;
import com.example.demo.model.vo.EngineResult;
import com.example.demo.model.vo.TreeNode;
import com.example.demo.service.engine.EngineBase;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 14:47
 * @Description:
 */
public class TreeEngineHandle extends EngineBase {

    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        // 决策流程
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);
        // 决策结果
        return new EngineResult(userId, treeId, treeNode.getTreeNodeId(), treeNode.getNodeValue());
    }
}

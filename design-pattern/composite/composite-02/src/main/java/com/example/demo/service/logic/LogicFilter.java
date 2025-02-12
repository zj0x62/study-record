package com.example.demo.service.logic;

import com.example.demo.model.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 14:47
 * @Description:
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLink> treeNodeLineInfoList);

    /**
     * 获取决策值
     *
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);
}

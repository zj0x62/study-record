package com.example.demo.service.logic.impl;

import com.example.demo.service.logic.BaseLogic;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 14:48
 * @Description:
 */
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("age");
    }
}

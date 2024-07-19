package com.example.leaf.segment.dao;

import com.example.leaf.segment.model.LeafAlloc;

import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 10:14
 * @Description:
 */
public interface IDAllocDao {

    List<LeafAlloc> getAllLeafAllocs();

    LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);

    LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);

    List<String> getAllTags();
}

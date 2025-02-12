package com.example.demo;

import com.example.demo.ceiling.LevelOneCeiling;
import com.example.demo.ceiling.LevelTwoCeiling;
import com.example.demo.coat.DuluxCoat;
import com.example.demo.coat.LiBangCoat;
import com.example.demo.floor.ShengXiangFloor;
import com.example.demo.tile.DongPengTile;
import com.example.demo.tile.MarcoPoloTile;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 14:07
 * @Description:
 */
public class Builder {

    public IMenu levelOne(Double area) {
        return new DecorationPackageMenu(area, "欧式豪华")
                .appendCeiling(new LevelOneCeiling())   // 吊顶，二级顶
                .appendCoat(new DuluxCoat())            // 涂料，多乐士
                .appendFloor(new ShengXiangFloor());    // 地板，圣象
    }

    public IMenu levelTwo(Double area) {
        return new DecorationPackageMenu(area, "轻奢田园")
                .appendCeiling(new LevelTwoCeiling())   // 吊顶，二级顶
                .appendCoat(new LiBangCoat())           // 涂料，立邦
                .appendTile(new MarcoPoloTile());       // 地砖，马可波罗
    }

    public IMenu levelThree(Double area){
        return new DecorationPackageMenu(area, "现代简约")
                .appendCeiling(new LevelOneCeiling())   // 吊顶，二级顶
                .appendCoat(new LiBangCoat())           // 涂料，立邦
                .appendTile(new DongPengTile());        // 地砖，东鹏
    }
}

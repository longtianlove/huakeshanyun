package com.stys.ipfs.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stys.ipfs.dto.EnumInfo;

public enum ResourceType {

    DIRECTORY("directory","目录"),
    MENU("menu","菜单"),
    BUTTON("button","按钮");

    private String code;

    private String name;

    private ResourceType(String code, String name){
        this.code = code;
        this.name = name; 
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static List<EnumInfo> getAllEnumInfo() {
        List<EnumInfo> list = new ArrayList<>();
        for (ResourceType rt : Arrays.asList(ResourceType.values())){
            list.add(new EnumInfo(rt.getCode(),rt.getName()));
        }
        return list;
    }

}
package com.stys.ipfs.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionInfo implements Serializable {

    private static final long serialVersionUID = -1886827721229436347L;

    private String title;

    private String value;

    private boolean checked;

    private boolean disabled;

    private List<PermissionInfo> data = new ArrayList<>(); 

    
}
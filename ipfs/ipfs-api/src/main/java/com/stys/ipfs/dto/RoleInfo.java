package com.stys.ipfs.dto;

import java.io.Serializable;
import java.util.List;

import com.stys.ipfs.entity.Permission;
import com.stys.ipfs.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleInfo extends Role implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Permission> permissions;

   
}
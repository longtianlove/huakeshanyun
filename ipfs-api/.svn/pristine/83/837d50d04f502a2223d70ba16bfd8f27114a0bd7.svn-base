package com.stys.ipfs.dto;

import java.io.Serializable;

import com.stys.ipfs.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleInfo roleInfo;

    
    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.getUserName() + this.getSalt();
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}
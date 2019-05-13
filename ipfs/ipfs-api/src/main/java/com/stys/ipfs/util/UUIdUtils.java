package com.stys.ipfs.util;

import java.util.UUID;

public class UUIdUtils {
	
	/**获取UUId
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

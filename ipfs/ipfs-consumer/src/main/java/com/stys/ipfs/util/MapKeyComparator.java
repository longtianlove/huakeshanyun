package com.stys.ipfs.util;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<String> {

	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2); // 升序排序
		// return str2.compareTo(str1); 降序排序
	}

}
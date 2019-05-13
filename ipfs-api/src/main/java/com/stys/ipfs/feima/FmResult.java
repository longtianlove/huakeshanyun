package com.stys.ipfs.feima;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FmResult {

	private String code;

	private String msg;

	private FmUserBean fmUserBean;

}

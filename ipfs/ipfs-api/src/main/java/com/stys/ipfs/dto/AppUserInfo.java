package com.stys.ipfs.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserInfo {

	private Serializable sessionId;
	private String appDownload;

 

}

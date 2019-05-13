package com.stys.ipfs.util;
import com.stys.ipfs.service.IArticleService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbInviteService;

public class ServiceUtils {
	public static IDicService idicService =SpringContextHolder.getBean(IDicService.class);
	public static ITbInviteService itbInviteService= SpringContextHolder.getBean(ITbInviteService.class);
	public static IArticleService iarticleService= SpringContextHolder.getBean(IArticleService.class);

}

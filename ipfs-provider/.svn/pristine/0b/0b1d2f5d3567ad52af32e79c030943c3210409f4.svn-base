//package com.stys.ipfs.config;
//
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.stys.ipfs.entity.AppUser;
//import com.stys.ipfs.entity.TbInvite;
//import com.stys.ipfs.entity.TbUserBuyLog;
//import com.stys.ipfs.service.IAppUserService;
//import com.stys.ipfs.service.IDicService;
//import com.stys.ipfs.service.ITbAssetsDetailService;
//import com.stys.ipfs.service.ITbInviteService;
//import com.stys.ipfs.service.ITbUserBuyLogService;
//import com.stys.ipfs.service.ITbVipminerService;
//import com.stys.ipfs.util.DateUtil;
//
//@Component
//@Configurable
//@EnableScheduling
//public class MonthProcessScheduledTask {
//
//	@Reference(version = "1.0.0", check = false)
//	private ITbUserBuyLogService itbUserBuyLogService;
//
//	@Reference(version = "1.0.0", check = false)
//	private IDicService idicService;
//
//	@Reference(version = "1.0.0", check = false)
//	private ITbVipminerService itbVipminerService;
//
//	@Reference(version = "1.0.0", check = false)
//	private ITbAssetsDetailService itbAssetsDetailService;
//
//	@Reference(version = "1.0.0", check = false)
//	private IAppUserService iappUserService;
//
//	@Reference(version = "1.0.0", check = false)
//	private ITbInviteService itbInviteService;
//
//	
//	private Logger logger = Logger.getLogger(getClass());
//	/**
//	 * 0 0 10 * * ? 每天10点触发一次
//	 * 
//	 * 0 * // 1 * * * ? 每天23点收益处理
//	 */
//	@Scheduled(cron = " 0 0 23 * * ? ")
//	public void reportCurrentByCron() {
//
//		// 每天23点判断， 查询所有所有人，一个月的时间是否有邀请人，
//
//		// List<TbVipminer> vipcount = itbVipminerService.selectList(new
//		// EntityWrapper<TbVipminer>().eq("status", 1));
//		List<AppUser> appuserList = iappUserService.selectList(new EntityWrapper<AppUser>());
//
//		List<TbUserBuyLog> tbUserBuyLogList = itbUserBuyLogService
//				.selectList(new EntityWrapper<TbUserBuyLog>().eq("status", 1));
//		if (null != tbUserBuyLogList) {
//			for (TbUserBuyLog tbUserBuyLog : tbUserBuyLogList) {
//				Date createtime = tbUserBuyLog.getCreateTime();
//
//				// 月
//				int month_num = tbUserBuyLog.getMonthNum();
//
//				// 日期差
//				boolean flag = DateUtil.isBeforeNowDay(createtime, month_num);
//
//				// 如果小于等于当前时间,那么判断是否等于当前时间
//
//				if (flag) {
//					// 处理流程
//
//					int user_id = tbUserBuyLog.getUserId();
//					String newYMD = DateUtil.fomatDate(createtime);
//					//增加月
//					String new_date=DateUtil.addMonthtoString(newYMD, month_num); 
//					
//					// 查询自创建账号之后的一个月起，是否有邀请人或是
//					List<TbInvite> inviteList = itbInviteService.selectList(new EntityWrapper<TbInvite>()
//							.eq("tb_iviter", user_id).like("create_time", "%" + newYMD + "%"));
//
//					if (null == inviteList && inviteList.size() < 1) {
//
//						//修改状态 
//						if (iappUserService.updateStatus(user_id, 0)) {
//							logger.error("app user："+user_id+" without  invite ！so  kick out  status:0");
//						}
//					}else {
//						tbUserBuyLog.setMonthNum(month_num+1);
//						itbUserBuyLogService.updateById(tbUserBuyLog); 
//					}
//
//				}else {
//					//判断用户状态，如果用户状态是使用（status=1）
//				
//					
//					AppUser  appuser=  iappUserService.selectById(tbUserBuyLog.getUserId());
//					appuser.setState(0);
//					
//					if(iappUserService.updateById(appuser)) {
//						logger.error("app user： updated status");
//					} 
//					
//				}
//
//				// 判断是否出局 合适日期
//
//			}
//		}
//
//		// 月末判断
//		Boolean flag = DateUtil.isLastDayOfMonth(new Date());
//		if (flag) {
//			// 查询用户在这个月，是否有邀请人， 如果有邀请人 不处理，否则
//
//		}
//
//	}
//}

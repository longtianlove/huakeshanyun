package com.stys.ipfs.web.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.Article;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.entity.TbBanner;
import com.stys.ipfs.entity.TbProduct;
import com.stys.ipfs.service.IArticleService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbBannerService;
import com.stys.ipfs.service.ITbInviteService;
import com.stys.ipfs.service.ITbProductService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.DateUtil;

@RestController
@RequestMapping("/tbr")
public class AppAdvertController extends AppController {

	@Reference(version = "1.0.0", check = false)
	private ITbBannerService itbBannerService;

	@Reference(version = "1.0.0", check = false)
	private ITbProductService itbProductService;

	@Reference(version = "1.0.0", check = false)
	private ITbInviteService itbInviteService;
	
	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;
	
	@Reference(version = "1.0.0", check = false)
	private IArticleService iarticleService;
	
	/**
	 * @api {post} /tbr/getBanners 轮播广告
	 * @apiGroup Business
	 * @apiDescription 详细描述：首页轮播图
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getBanners
	 * @return
	 */
	@RequestMapping(value = "/getBanners", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<List<TbBanner>> getBanners() {
		EntityWrapper<TbBanner> entity = new EntityWrapper<TbBanner>();
		entity.eq("state", 1);
		List<TbBanner> list = itbBannerService.selectList(entity);
		return new AppResultInfo<>(list, AppConstant.STATUE_200);
	}

	/**
	 * 
	 * @api {post} /tbr/getProductList 商品列表(分页)
	 * @apiGroup Business
	 * @apiDescription 详细描述：商品列表
	 * @apiParam {Integer} page 当前页
	 * @apiParam {Integer} limit 每页多少条
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getProductList
	 * @return
	 */

	@RequestMapping(value = "/getProductList", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> getProductList(Integer page, Integer limit) {
		EntityWrapper<TbProduct> entity = new EntityWrapper<TbProduct>();
		if(limit==0||limit==null) {
			limit=10;
		}
		entity.eq("status", 1);
		Page<TbProduct> pageObj = itbProductService.selectPage(new Page<TbProduct>(page,limit),entity);
		return new AppResultInfo<>(pageObj);
	}
	
	/**
	 * 获取商品详细信息
	 * @api {post} /tbr/getProductDetail 获取商品详细信息
	 * @apiGroup Business
	 * @apiDescription 详细描述：获取商品详细信息
	 * @apiParam {Integer} productId  商品Id
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getProductDetail
	 * @return
	 */
	@RequestMapping(value = "/getProductDetail", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> getProductDetail(Integer productId){
		TbProduct product=itbProductService.selectById(productId);
		return new AppResultInfo<>(product);
	}
	
	/**
	 * @api {post} /tbr/getNotice 通知功能接口
	 * @apiDescription 通知消息，滚动播出
	 * @apiGroup Business
	 * @apiSampleRequest http://127.0.0.1/tbr/getNotice
	 * 
	 */
	@RequestMapping(value = "/getNotice", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<List<Article>> getNoticeData() {
		Dic dic = idicService.getDicData("图文管理", "公告");
		Dic dic2 = idicService.getDicData("图文管理", "购买信息");
		List<Article> articles =new ArrayList<>();
		List<Article> sels =iarticleService.getArticleList(dic2.getId(),5);
		if(sels!=null&&sels.size()>0) {
			articles=sels;
		}else {
			articles= iarticleService.selectList(new EntityWrapper<Article>().eq("dic_id", dic.getId()));
		}
		return new AppResultInfo<>(articles);
	}
	/**
	 * @api {post} /tbr/getNoticeDetail 点击头条展示信息
	 * @apiDescription 通知消息,点击头条展示信息
	 * @apiGroup Business
	 * @apiSampleRequest http://127.0.0.1/tbr/getNoticeDetail
	 * 
	 */
	@RequestMapping(value = "/getNoticeDetail", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<List<Article>> getNoticeDetail() {
		Dic dic2 = idicService.getDicData("图文管理", "购买信息");
		List<Article> list=iarticleService.getArticleList(dic2.getId(),20);
		return new AppResultInfo<>(list);
	}

}

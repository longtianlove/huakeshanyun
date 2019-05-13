package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.dto.UserInfo;
import com.stys.ipfs.entity.User;
import com.stys.ipfs.service.IUserService;
import com.stys.ipfs.util.PasswordEncoder;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-10
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Reference(version = "1.0.0",check=false)
	private IUserService iUserService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/unlock")
	@ResponseBody
	public ResultInfo<Boolean> unlock(@RequestParam("password") String password) {
		UserInfo userInfo = this.getUserInfo();
		SimpleHash simpleHash = new SimpleHash("md5", password, userInfo.getCredentialsSalt(), 2);
		if (simpleHash.toString().equals(userInfo.getPassWord())) {
			return new ResultInfo<>(true);
		}
		return new ResultInfo<>(false);
	}

	@RequestMapping("/listData")
	@RequiresPermissions("user:view")
	public @ResponseBody ResultInfo<List<User>> listData(User user, Integer page, Integer limit) {
		EntityWrapper<User> wrapper = new EntityWrapper<>(user);
		if (user != null && user.getUserName() != null) {
			wrapper.like("user_name", user.getUserName());
			user.setUserName(null);
		}
		if (user != null && user.getName() != null) {
			wrapper.like("name", user.getName());
			user.setName(null);
		}
		Page<User> pageObj = iUserService.selectPage(new Page<User>(page, limit), wrapper);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("user:add")
	public @ResponseBody ResultInfo<Boolean> add(User user) {
		Map<String, String> map = PasswordEncoder.enCodePassWord(user.getUserName(), user.getPassWord());
		user.setSalt(map.get(PasswordEncoder.SALT));
		user.setPassWord(map.get(PasswordEncoder.PASSWORD));
		boolean b = iUserService.insert(user);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("user:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = iUserService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("user:edit")
	public @ResponseBody ResultInfo<Boolean> edit(User user) {
		User us = iUserService.selectById(user.getId());
		us.setName(user.getName());
		us.setRoleId(user.getRoleId());
		us.setState(user.getState());
		boolean b = iUserService.updateById(us);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/userEdit")
	public @ResponseBody ResultInfo<Boolean> userEdit(User user) {
		UserInfo userInfo = this.getUserInfo();
		User us = iUserService.selectById(userInfo.getId());
		if (!StringUtils.isEmpty(user.getName())) {
			us.setName(user.getName());
		}
		if (!StringUtils.isEmpty(user.getPassWord())) {
			Map<String, String> map = PasswordEncoder.enCodePassWord(us.getUserName(), user.getPassWord());
			us.setSalt(map.get(PasswordEncoder.SALT));
			us.setPassWord(map.get(PasswordEncoder.PASSWORD));
		}
		boolean b = iUserService.updateById(us);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/centerDate")
	public @ResponseBody ResultInfo<UserInfo> centerDate() {
		UserInfo userInfo = this.getUserInfo();
		BeanUtils.copyProperties(iUserService.selectById(userInfo.getId()), userInfo);
		return new ResultInfo<>(userInfo);
	}

	@RequestMapping("/count")
	public @ResponseBody ResultInfo<Integer> count() { 
		return new ResultInfo<>(iUserService.selectCount(new EntityWrapper<User>()));
	}

}
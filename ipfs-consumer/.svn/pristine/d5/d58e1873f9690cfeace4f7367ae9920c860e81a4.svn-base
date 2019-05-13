package com.stys.ipfs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.dto.DirectoryInfo;
import com.stys.ipfs.dto.RoleInfo;
import com.stys.ipfs.dto.UserInfo;
import com.stys.ipfs.entity.Permission;
import com.stys.ipfs.entity.User;
import com.stys.ipfs.ex.BusinessException;
import com.stys.ipfs.service.ILoginLogService;
import com.stys.ipfs.service.IPermissionService;
import com.stys.ipfs.service.IUserService;
import com.stys.ipfs.util.Constant;

/**
 * @author  
 */
@Controller
public class HomeController extends BaseController{

    @Reference(version = "1.0.0",check=false)
    private IPermissionService iPermissionService;

    @Reference(version = "1.0.0",check=false)
    private IUserService iUserService;

    @Reference(version = "1.0.0",check=false)
    private ILoginLogService iloginLogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping({"/","/index"})
    public String index(Model model){

        List<DirectoryInfo> directoryList = new ArrayList<>();
        //获取当前用户角色信息
        UserInfo userInfo = this.getUserInfo();
        RoleInfo roleInfo = userInfo.getRoleInfo();
        List<Permission> permissionList = iPermissionService.getAllDirectoryPermissions();
        if(permissionList != null){
            for (Permission ps : permissionList) {
                if(roleInfo.getPermissionIds().contains(","+ps.getId()+",")){
                	System.out.println("------------"+ps.getPermissionName()+"\n");
                    directoryList.add(new DirectoryInfo(ps.getPermissionName(),ps.getPermissionCode()));
                }
            }
        }
        model.addAttribute("directoryList",directoryList);

        return "index";
    }
    @RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";  
    }

    
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickOut() {
        return "login"; 
    }
    //@RequestMapping("/login")
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpServletRequest request) throws BusinessException {

        logger.info("HomeController.login()");

        // 判断是否已登录，如果已登录直接跳转到首页
        UserInfo userInfo = this.getUserInfo();
        if (userInfo != null){
            return "redirect:/";
        }

        // 登录失败从request中获取shiro处理的异常信息.
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        logger.info("exception=" + exception);
        Session session = SecurityUtils.getSubject().getSession();
        if (exception != null) {
            if (AccountException.class.getName().equals(exception)) {
                logger.info("AccountException ---> 账号或密码错误！");
                throw new BusinessException("1", "账号或密码错误！");
            }else if(IncorrectCredentialsException.class.getName().equals(exception)){
               if(null==session.getAttribute(Constant.LOGIN_ERROR_COUNT)) {
            	   logger.info("AccountException ---> 登录次数错误！");
                   throw new BusinessException(Constant.YES_ERROR, "密码错误"); 
               }
            	//密码最多错误输入5次
                int loginErrorCount = Integer.parseInt(session.getAttribute(Constant.LOGIN_ERROR_COUNT) + "");
                if(++loginErrorCount == Constant.MAX_LOGIN_ERROR_NUM){
                    //锁定账号
                    User user = iUserService.selectById(Integer.parseInt(session.getAttribute(Constant.LOGIN_USER_ID) + ""));
                    user.setState(2);
                    iUserService.updateById(user);
                }
                session.setAttribute(Constant.LOGIN_ERROR_COUNT, loginErrorCount);
                logger.info("AccountException ---> 密码错误！");
                throw new BusinessException(Constant.YES_ERROR, "密码错误，您还有" + (Constant.MAX_LOGIN_ERROR_NUM - loginErrorCount) + "机会！");
            }
            else if(DisabledAccountException.class.getName().equals(exception)) {
                logger.info("DisabledAccountException ---> 账号已禁用！");
                throw new BusinessException(Constant.YES_ERROR, "账号已禁用！");
            } else if(LockedAccountException.class.getName().equals(exception)) {
                logger.info("LockedAccountException ---> 账号已锁定！");
                throw new BusinessException(Constant.YES_ERROR, "账号已锁定，请联系管理员解锁！");
            } else if ("kaptchaValidateFailed".equals(exception)) {
                logger.info("kaptchaValidateFailed ---> 验证码错误！");
                throw new BusinessException(Constant.YES_ERROR, "验证码错误！");
            } else {
                logger.info("else ---> " + exception);
                throw new BusinessException(Constant.YES_ERROR, "未知错误！");
            }
        }
        //初始化登录错误次数
        session.setAttribute(Constant.LOGIN_ERROR_COUNT, 0);
        //记录登录IP地址
        session.setAttribute(Constant.LOGIN_IP_ADDRESS, this.getIpAddress(request));

        // 此方法不处理登录成功,由shiro进行处理

        return "/login";
    }

}
package com.stys.ipfs.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.dto.UserInfo;
import com.stys.ipfs.entity.LoginLog;
import com.stys.ipfs.entity.Permission;
import com.stys.ipfs.service.ILoginLogService;
import com.stys.ipfs.service.IUserService;
//import com.stys.ipfs.util.AddressUtils;
import com.stys.ipfs.util.Constant;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0",check=false)
    private IUserService iUserService;

    @Reference(version = "1.0.0",check=false)  
    private ILoginLogService iloginLogService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info("权限配置----->ShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        authorizationInfo.addRole(userInfo.getRoleInfo().getRoleCode());
        for(Permission p:userInfo.getRoleInfo().getPermissions()){
            authorizationInfo.addStringPermission(p.getPermissionCode());
        }

        //授权成功添加登录日志
        addLoginLog(userInfo);

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        logger.info("ShiroRealm.doGetAuthenticationInfo()");

        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = iUserService.findUserInfo(username);

        if(userInfo == null){
            throw new AccountException();
        }else if(userInfo.getState() == 0){
            throw new DisabledAccountException();
        }else if(userInfo.getState() == 2){
            throw new LockedAccountException();
        }

        //保存登录用户ID
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(Constant.LOGIN_USER_ID, userInfo.getId());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户信息
                userInfo.getPassWord(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        return authenticationInfo;
    }

    private void addLoginLog(UserInfo userInfo) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userInfo.getId());
        loginLog.setUserName(userInfo.getUserName());
        if(null!=SecurityUtils.getSubject().getSession().getAttribute(Constant.LOGIN_IP_ADDRESS)) {
        	 loginLog.setIpAddress(SecurityUtils.getSubject().getSession().getAttribute(Constant.LOGIN_IP_ADDRESS).toString());
        }else {
        	 loginLog.setIpAddress("127.0.0.1");
        }
       
      //  loginLog.setGeographyLocation(AddressUtils.getAddressByIp(loginLog.getIpAddress()));
        iloginLogService.insert(loginLog);
    }

}
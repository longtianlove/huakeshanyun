package com.stys.ipfs.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
@Import({ ConverterConfig.class })
public class ShiroConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		logger.info("ShiroConfiguration.shirFilter()");

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");

		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");

		// 未授权界面;
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		// 限制同一账号同时在线的个数。
		filtersMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		Map<String, String> filterChainDefinitionMap = filterChainMap(shiroFilterFactoryBean);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	private Map<String, String> filterChainMap(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		// 获取filters
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		// 将自定义的FormAuthenticationFilter注入shiroFilter中（验证码校验）
		filters.put("authc", new CustomFormAuthenticationFilter());

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/download", "anon");
		filterChainDefinitionMap.put("/login2", "anon");
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/kaptcha/**", "anon");
		filterChainDefinitionMap.put("/tbr/sendMsg", "anon");
		filterChainDefinitionMap.put("/appUser/buy.html", "anon");
		filterChainDefinitionMap.put("/tbr/**", "anon");
		filterChainDefinitionMap.put("/invite/**", "anon");
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/invite/login.html", "anon");
		filterChainDefinitionMap.put("/tbOfflinePayment/recharge.html", "anon");
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

		filterChainDefinitionMap.put("/sign/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");

		return filterChainDefinitionMap;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * 自定义身份认证realm
	 * 
	 * @return
	 */
	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return shiroRealm;
	}

	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	@Bean
	public CustomRedisManager customRedisManager() {
		return new CustomRedisManager();
	}

	/**
	 * cacheManager缓存 redis实现 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManagers() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(customRedisManager());
		return redisCacheManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(customRedisManager());
		return redisSessionDAO;
	}

	/**
	 * shiro session管理
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// Collection<org.apache.shiro.session.SessionListener> listeners=new
		// ArrayList<>();
		// listeners.add(new SessionListener());
		// sessionManager.setSessionListeners(listeners);
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	/**
	 * 限制同一账号登录同时登录人数控制
	 *
	 * @return
	 */

	@Bean
	public KickoutSessionControlFilter kickoutSessionControlFilter() {
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		kickoutSessionControlFilter.setCacheManager(cacheManagers());
		kickoutSessionControlFilter.setSessionManager(sessionManager());
		kickoutSessionControlFilter.setKickoutAfter(false);
		kickoutSessionControlFilter.setMaxSession(1);
		kickoutSessionControlFilter.setKickoutUrl("/403");
		return kickoutSessionControlFilter;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManagers());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());

		// 设置realm
		securityManager.setRealm(shiroRealm());
		// ----------11-8 dp---------------------
		// 设置不创建session

		/*
		 * securityManager.setSubjectFactory(subjectFactory()); //
		 * 无状态需要设置不创建session，禁用使用Sessions //
		 * 作为存储策略的实现，但它没有完全地禁用Sessions，所以需要配合context.setSessionCreationEnabled(false);
		 * ((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO)
		 * securityManager.getSubjectDAO())
		 * .getSessionStorageEvaluator()).setSessionStorageEnabled(false);
		 */
		// ------------11-8 dp----------------

		return securityManager;
	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * 
	 * @return
	 */
//	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
		return lifecycleBeanPostProcessor;
	}

	/**
	 * 必须加否则shiro注解无法使用
	 * 
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	/**
	 * 启用{@value}
	 * 
	 * @return
	 */
	/*
	 * @Bean public static PropertySourcesPlaceholderConfigurer
	 * propertySourcesPlaceholderConfigurer() { return new
	 * PropertySourcesPlaceholderConfigurer(); }
	 */

	/**
	 * 
	 * 
	 * shiro 方言配置
	 * 
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

}
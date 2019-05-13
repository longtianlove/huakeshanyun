package com.stys.ipfs.config;

import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AbstractLocaleContextResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

@Configuration
@ComponentScan
public class I18nConfig extends AbstractLocaleContextResolver {
	public static final String LOCALE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".LOCALE";
	public static final String TIME_ZONE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".TIME_ZONE";

	/** 国际化文件路径 */
	@Value("${spring.messages.basename}")
	public String[] basefilenames;

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolverBean() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return sessionLocaleResolver;
	}

	/**
	 * 用于解析消息的策略接口，支持这些消息的参数化和国际化。
	 */
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		if (basefilenames != null) {
			for (int i = 0; i < basefilenames.length; i++) {
				String basename = basefilenames[i];
				Assert.hasText(basename, "Basename must not be empty");
				this.basefilenames[i] = basename.trim();
			}
			source.setBasenames(basefilenames);
		} else {
			this.basefilenames = new String[0];
			source.setBasename(basefilenames[0]);
		}
		source.setDefaultEncoding("UTF-8");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		this.setLocaleContext(request, response, locale != null ? new SimpleLocaleContext(locale) : null);
	}

	@Override
	public LocaleContext resolveLocaleContext(HttpServletRequest request) {
		return null;
	}

	@Override
	public void setLocaleContext(HttpServletRequest request, HttpServletResponse response,
			LocaleContext localeContext) {
		Locale locale = null;
		TimeZone timeZone = null;
		if (localeContext != null) {
			locale = localeContext.getLocale();
			if (localeContext instanceof TimeZoneAwareLocaleContext) {
				timeZone = ((TimeZoneAwareLocaleContext) localeContext).getTimeZone();
			}
		}
		WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		WebUtils.setSessionAttribute(request, TIME_ZONE_SESSION_ATTRIBUTE_NAME, timeZone);
	}

}

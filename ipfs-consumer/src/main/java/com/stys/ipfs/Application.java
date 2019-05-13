package com.stys.ipfs;

import java.util.Properties;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.stys.ipfs.util.SpringContextHolder;

@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAsync
public class Application {

	@Value("${server.port}")
	private Integer port;

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
		connector.setPort(80);
		return connector;
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(443);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SpringContextHolder getSpringContextHolder() {
		return new SpringContextHolder();
	}

	@Bean
	public DefaultKaptcha captchaProducer() {
		DefaultKaptcha captchaProducer = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty(Constants.KAPTCHA_BORDER, "no");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "32");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
		properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "116");
		properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "36");
		properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "code");
		Config config = new Config(properties);
		captchaProducer.setConfig(config);
		return captchaProducer;
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	/**
	 * 跨域过滤器
	 *
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		return new CorsFilter(source);
	}
}

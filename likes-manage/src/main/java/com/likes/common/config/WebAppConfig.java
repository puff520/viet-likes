package com.likes.common.config;

import com.likes.common.interceptor.SystemInterceptor;
import com.likes.common.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

	@Resource
	private SystemInterceptor systemInterceptor;
	@Resource
	private RequestMappingHandlerAdapter handlerAdapter;
	@Resource
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	public SpringContextUtil newConfigure() {
		return new SpringContextUtil();
	}

	/**
	 * @author ?????? ??????????????????RedisMessageListenerContainer??????IOC??????
	 * @return
	 */
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		return container;
	}

	/**
	 * <br>
	 * ??????
	 * 
	 * @Endpoint?????????websocket??????ServerEndpointExporter??????????????????
	 * 
	 * @return
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	/**
	 * ????????????servlet??????
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", buildConfig());
//		return new CorsFilter(source);
//	}
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	@Bean
	@Order(0)
	public BeanNameViewResolver beanNameViewResolver() {
		return new BeanNameViewResolver();
	}

	/**
	 * ?????????????????????????????????
	 */
	@PostConstruct
	public void initEditableValidation() {
		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
		if (initializer.getConversionService() != null) {
			GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
			genericConversionService.addConverter(new StringToDateConverter());
		}
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ByteArrayHttpMessageConverter());
		// converters.add(new StringHttpMessageConverter());
		// converters.add(new MappingJackson2HttpMessageConverter());

		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.TEXT_HTML);

		FormHttpMessageConverter converter = new FormHttpMessageConverter();
		converter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(converter);

		StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter();
		stringMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(stringMessageConverter);

		MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();
		jacksonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(jacksonMessageConverter);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// ???????????????????????????????????????
		// addPathPatterns ????????????????????????
		// excludePathPatterns ??????????????????
		// registry.addInterceptor(authorizationInterceptor);
		registry.addInterceptor(systemInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/swagger-ui.html","/swagger**/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// ?????????/static/** ??????????????????classpath:/static/ ?????????
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		// swagger2
		//registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		//registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


}

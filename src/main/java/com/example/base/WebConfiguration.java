/**
 *
 */
package com.example.base;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * @author Salman
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	private static final String UTF_8 = "UTF-8";
	private static final String CLASSPATH_LOCALE_MESSAGES = "classpath:locale/messages";

	@Override
	public Validator getValidator() {
		return validator();
	}

	@Bean
	public Validator validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		bean.setBasename(CLASSPATH_LOCALE_MESSAGES);
		bean.setDefaultEncoding(UTF_8);
		bean.setUseCodeAsDefaultMessage(true);
		return bean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
		return acceptHeaderLocaleResolver;
	}

}

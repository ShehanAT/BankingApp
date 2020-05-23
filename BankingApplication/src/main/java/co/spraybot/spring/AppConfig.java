package co.spraybot.spring;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	// class for defining beans
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieResolver = new CookieLocaleResolver();
		cookieResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieResolver;
	}
}

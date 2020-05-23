package co.spraybot.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"co.spraybot.web"})
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{

	@Autowired
	public MessageSource messageSource;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/login");
		registry.addViewController("/login").setViewName("/user/login"); // maps url pattern to view template without controller in between
		registry.addViewController("/registration").setViewName("forward:/user/registration");
		registry.addViewController("/invalidSession").setViewName("/invalidSession.html");
		registry.addViewController("/invalidSession.html");
		registry.addViewController("/homepage").setViewName("forward:/homepage");
	}
	
	@Bean
	@ConditionalOnMissingBean(RequestContextListener.class)
	public RequestContextListener requestContentListener() {
		return new RequestContextListener();
	}
}

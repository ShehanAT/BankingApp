package co.spraybot.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import co.spraybot.dao.CustomerRepository;
import co.spraybot.security.CustomAuthenticationProvider;
import co.spraybot.security.CustomLogoutSuccessHandler;
import co.spraybot.security.CustomWebAuthenticationDetailsSource;


@Configuration
@ComponentScan(basePackages= {"co.spraybot.security"})
//@ImportResource({ "classpath:src/main/resources/webSecurityConfig.xml" })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService customerDetailsService; // loads customer object from db
	
	@Bean 
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
	
	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler myAuthenticationFailureHandler;
	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomWebAuthenticationDetailsSource authenticationDetailsSource;
	
	
	public SecurityConfig() {
		super();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
//		super.configure(authenticationProvider);
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/h2/**").permitAll()
		.antMatchers("/login*", "/login*", "/logout*", "/signin/**", "/customLogin").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
//		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/homepage")
		.successHandler(myAuthenticationSuccessHandler)
		.failureHandler(myAuthenticationFailureHandler)
		.failureUrl("/login?error=true")
		.authenticationDetailsSource(authenticationDetailsSource)
		.permitAll()
		.and()
		.sessionManagement()
		.invalidSessionUrl("/invalidSession.html")
		.maximumSessions(1).sessionRegistry(sessionRegistry())
		.and()
		.sessionFixation().none()
		.and()
		.headers().frameOptions().disable()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
//// NOTE: implement logoutSuccessHandler after login
		.logoutSuccessHandler(customLogoutSuccessHandler)
		.logoutSuccessUrl("/logout.html?logSucc=true")
		.permitAll();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
		authProvider.setUserDetailsService(customerDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	
	@Bean 
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
		// cannot return an interface, must be class
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}

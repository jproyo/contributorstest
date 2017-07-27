package edu.jproyo.dojos.contributorstest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import edu.jproyo.dojos.contributorstest.security.CustomRequestHeaderAuthFilter;
import edu.jproyo.dojos.contributorstest.security.TokenAuthenticationProvider;

/**
 * The Class Security.
 */
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	
	/** The application event publisher. */
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(filter())
        		.authorizeRequests()
				.anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("token realm=\"contributorstest\""))
				.and().csrf().disable();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider());
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	/**
	 * Event publisher.
	 *
	 * @return the authentication event publisher
	 */
	@Bean
	public AuthenticationEventPublisher eventPublisher() {
		DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher = new DefaultAuthenticationEventPublisher(applicationEventPublisher);
		return defaultAuthenticationEventPublisher;
	}
	
	
	/**
	 * Provider.
	 *
	 * @return the pre authenticated authentication provider
	 */
	@Bean
	public PreAuthenticatedAuthenticationProvider provider() {
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(tokenAuthenticationProvider);
		provider.setThrowExceptionWhenTokenRejected(false);
		return provider;
	}
	
	
	/**
	 * Filter.
	 *
	 * @return the request header authentication filter
	 * @throws Exception 
	 */
	@Bean
	public RequestHeaderAuthenticationFilter filter() throws Exception {
		CustomRequestHeaderAuthFilter provider = new CustomRequestHeaderAuthFilter();
		provider.setAuthenticationManager(authenticationManagerBean());
		return provider;
	}

	
}

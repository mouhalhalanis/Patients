package com.emsi.demo2.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
private DataSource dataSource;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.formLogin();
		 http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
		 http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");
		 http.authorizeRequests().antMatchers("/login", "/logout").permitAll();
	     http.authorizeRequests().anyRequest().authenticated();
	     http.csrf();
	     http.exceptionHandling().accessDeniedPage("/forbidden");
  
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	    PasswordEncoder  passwordEncoder=passwordEncoder();
		System.out.println("---------");
		System.out.println(passwordEncoder.encode("1234"));
		System.out.println("---------");
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(passwordEncoder);
		
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	}
}

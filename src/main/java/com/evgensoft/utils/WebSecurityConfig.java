package com.evgensoft.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
				// Доступ только для не зарегистрированных пользователей
				.antMatchers("/signup").not().fullyAuthenticated()
				// Доступ только для пользователей с ролью Администратор
				.antMatchers("/api/**/delete", "/api/**/edit").hasRole("ADMIN").antMatchers("/api/book").hasRole("USER") // доступно
																															// только
																															// пользователю

				// Доступ разрешен всем пользователей
				.antMatchers("/", "/resources/**").permitAll()
				// Все остальные страницы требуют аутентификации
				.anyRequest().authenticated().and()
				// Настройка для входа в систему
				.formLogin().loginPage("/login")
				// Перенаправление на главную страницу после успешного входа
				.defaultSuccessUrl("/").permitAll().and().logout().permitAll().logoutSuccessUrl("/");
	}

}

package com.thongddps08464.assignmentjava5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thongddps08464.assignmentjava5.service.NguoiDungService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    NguoiDungService nguoiDungService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(nguoiDungService) // Cung cáp userservice cho spring security
            .passwordEncoder(passwordEncoder()); // Cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Cấu hình remember me, thời gian là 86400 giây = 1 ngày
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);
        
        // Không cho browser tự động cache
    	http
			.headers()
			.cacheControl();
    	
    	http
			.csrf()
				.disable()
        	.authorizeRequests()
        		.antMatchers("/assets/**").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
        		.anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
        		.and()
        		.formLogin() // Cho phép người dùng xác thực bằng form login
	        		.loginPage("/trang-dang-nhap").permitAll() // Tất cả đều được truy cập vào địa chỉ này
//	        		.defaultSuccessUrl("/")
	        		.loginProcessingUrl("/dang-nhap")
	        		.usernameParameter("tenDangNhap")
	                .passwordParameter("matKhau")
	        		.and()
        		.logout() // Cho phép logout
        			.invalidateHttpSession(true) // Hủy session của người dùng
        			.clearAuthentication(true)
        			.logoutUrl("/dang-xuat")
	                .logoutSuccessUrl("/trang-dang-nhap").permitAll();
    }
    
    
}

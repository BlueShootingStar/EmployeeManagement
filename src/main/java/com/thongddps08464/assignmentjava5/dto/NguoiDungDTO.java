package com.thongddps08464.assignmentjava5.dto;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thongddps08464.assignmentjava5.model.NguoiDung;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NguoiDungDTO implements UserDetails {
	private static final long serialVersionUID = -6016240365563310323L;
	
	private NguoiDung nguoiDung;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return nguoiDung.getMatKhau();
    }

    @Override
    public String getUsername() {
        return nguoiDung.getTenDangNhap();
    }

    public String getHoTen() {
    	return nguoiDung.getHoTen();
    }
    
    public String getEmail() {
    	return nguoiDung.getEmail();
    }
    
    public String getHinh() {
    	return nguoiDung.getHinh();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

package com.ysm.demo.security.dto;

import com.ysm.demo.config.principal.UserPrincipalProperties;
import com.ysm.demo.init.database.tables.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

  private User user;

  public UserPrincipal(User user){
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();

    this.user.getPermissionList().forEach(p -> {
      GrantedAuthority authority = new SimpleGrantedAuthority(p);
      authorities.add(authority);
    });

    this.user.getRoleList().forEach(p -> {
      GrantedAuthority authority = new SimpleGrantedAuthority(UserPrincipalProperties.grantedPrefix + p);
      authorities.add(authority);
    });

    return authorities;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return LocalDateTime.now().isBefore(this.user.getExpiredDate());
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.user.getLoginFailCnt() <= UserPrincipalProperties.loginFailCount;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return LocalDateTime.now().minusMonths(UserPrincipalProperties.passwordChangeMonth).isBefore(this.user.getPasswordChangeDate());
  }

  @Override
  public boolean isEnabled() {
    return "Y".equals(this.user.getActiveYn());
  }
}

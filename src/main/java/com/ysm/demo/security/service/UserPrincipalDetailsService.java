package com.ysm.demo.security.service;

import com.ysm.demo.init.database.tables.USER;
import com.ysm.demo.security.dto.UserPrincipal;
import com.ysm.demo.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
   private UserRepository userRepository;

   public UserPrincipalDetailsService(UserRepository userRepository){
     this.userRepository = userRepository;
   }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    USER user = this.userRepository.findByUsername(username);
    UserPrincipal userPrincipal = new UserPrincipal(user);

    return userPrincipal;
  }
}

package com.ysm.demo.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ysm.demo.init.database.tables.USER;
import com.ysm.demo.security.dto.UserPrincipal;
import com.ysm.demo.security.repository.UserRepository;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private UserRepository userRepository;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    super(authenticationManager);
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String header = request.getHeader(JwtProperties.HEADER_STRING);

    if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
      chain.doFilter(request, response);

      return;
    }

    Authentication authentication = getUsernamePasswordAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    chain.doFilter(request, response);
  }

  private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
    String token = request.getHeader(JwtProperties.HEADER_STRING);
    if (token != null) {
      String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
          .build()
          .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
          .getSubject();

      if (username != null) {
        USER user = userRepository.findByUsername(username);
        UserPrincipal principal = new UserPrincipal(user);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,
            null, principal.getAuthorities());

        return auth;
      }

      return null;
    }

    return null;
  }
}

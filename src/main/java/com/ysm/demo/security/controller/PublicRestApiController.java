package com.ysm.demo.security.controller;

import com.ysm.demo.init.database.tables.User;
import com.ysm.demo.security.repository.UserRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
@Slf4j
public class PublicRestApiController {

  private final UserRepository userRepository;
  @Autowired private SqlSessionTemplate sqlSession;

  @GetMapping("test1")
  public String test1() {
    return "API Test 1";
  }

  @GetMapping("test2")
  public String test2() {
    return "API Test 2";
  }

  @GetMapping("admin/usersAll")
  public List<User> allUsers() {
    return this.userRepository.findAll();
  }

  @GetMapping("admin/usersAdmin")
  public User AdminUsers() {
    return this.userRepository.findByUsername("admin");
  }

  @GetMapping("admin/mybatis")
  public Object adminMybatis(){
    return sqlSession.selectOne("test.test");
  }
}

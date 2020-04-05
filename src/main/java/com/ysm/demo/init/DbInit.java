package com.ysm.demo.init;

import com.ysm.demo.security.dto.User;
import com.ysm.demo.security.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DbInit implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // delete all
    this.userRepository.deleteAll();

    // create user
    User admin = new User("admin", this.passwordEncoder.encode("1111"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
    User manager = new User("manager", this.passwordEncoder.encode("1111"), "MANAGER", "ACCESS_TEST1");
    User user = new User("user", this.passwordEncoder.encode("1111"), "USER", "");

    List<User> users = Arrays.asList(admin, manager, user);

    //save db
    this.userRepository.saveAll(users);
  }
}

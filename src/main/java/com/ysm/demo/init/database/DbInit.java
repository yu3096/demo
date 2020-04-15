package com.ysm.demo.init.database;

import com.ysm.demo.init.database.tables.User;
import com.ysm.demo.init.database.tables.dataClasses.UserPks;
import com.ysm.demo.security.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
    admin.setUserId(new UserPks(UUID.randomUUID()));
    User manager = new User("manager", this.passwordEncoder.encode("1111"), "MANAGER", "ACCESS_TEST1");
    manager.setUserId(new UserPks(UUID.randomUUID()));
    User user = new User("user", this.passwordEncoder.encode("1111"), "USER", "");
    user.setUserId(new UserPks(UUID.randomUUID()));
    List<User> users = Arrays.asList(admin, manager, user);

    //save db
    this.userRepository.saveAll(users);
  }
}

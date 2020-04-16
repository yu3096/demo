package com.ysm.demo.init.database;

import com.ysm.demo.init.database.tables.CommonCode;
import com.ysm.demo.init.database.tables.Menu;
import com.ysm.demo.init.database.tables.User;
import com.ysm.demo.init.database.tables.dataClasses.CommonCodePks;
import com.ysm.demo.init.database.tables.dataClasses.MenuPks;
import com.ysm.demo.init.database.tables.dataClasses.UserPks;
import com.ysm.demo.security.repository.CommonRepository;
import com.ysm.demo.security.repository.MenuRepository;
import com.ysm.demo.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DbInit implements CommandLineRunner {
    private final CommonRepository commonRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //Common Code
        this.commonRepository.deleteAll();

        CommonCode commonCode1 = new CommonCode(new CommonCodePks("MenuType", "10"), "일반", 1);
        CommonCode commonCode2 = new CommonCode(new CommonCodePks("MenuType", "10"), "팝업", 2);

        this.commonRepository.saveAll(Arrays.asList(commonCode1, commonCode2));

        //User
        this.userRepository.deleteAll();

        User admin = new User("admin", this.passwordEncoder.encode("1111"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        admin.setPasswordChangeDate(LocalDateTime.now().minusMonths(2));
        admin.setUserId(new UserPks(UUID.randomUUID()));
        User manager = new User("manager", this.passwordEncoder.encode("1111"), "MANAGER", "ACCESS_TEST1");
        manager.setUserId(new UserPks(UUID.randomUUID()));
        User user = new User("user", this.passwordEncoder.encode("1111"), "USER", "");
        user.setUserId(new UserPks(UUID.randomUUID()));
        List<User> users = Arrays.asList(admin, manager, user);

        this.userRepository.saveAll(users);

        //Menu
        this.menuRepository.deleteAll();
        Menu root = new Menu(new MenuPks(0L), null, "홈", "홈", "/", "00", 1);
        Menu system = new Menu(new MenuPks(1L), root.getMenuSeq(), "홈", "홈", "/", "00", 1);


        //this.menuRepository.saveAll(Arrays.asList(root, system));
    }
}

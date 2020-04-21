package com.ysm.demo.init.database;

import com.ysm.demo.init.database.tables.CommonCode;
import com.ysm.demo.init.database.tables.Menu;
import com.ysm.demo.init.database.tables.User;
import com.ysm.demo.init.database.tables.dataClasses.CommonCodePks;
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

        CommonCode commonCode1 = new CommonCode(new CommonCodePks("MenuType", "00"), "일반", 1);
        CommonCode commonCode2 = new CommonCode(new CommonCodePks("MenuType", "10"), "팝업", 2);

        this.commonRepository.saveAll(Arrays.asList(commonCode1, commonCode2));

        //User
        this.userRepository.deleteAll();

        User admin = new User("admin", this.passwordEncoder.encode("1111"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        admin.setPasswordChangeDate(LocalDateTime.now().minusMonths(2));
        User manager = new User("manager", this.passwordEncoder.encode("1111"), "MANAGER", "ACCESS_TEST1");
        User user = new User("user", this.passwordEncoder.encode("1111"), "USER", "");
        List<User> users = Arrays.asList(admin, manager, user);

        this.userRepository.saveAll(users);

        //Menu
        this.menuRepository.deleteAll();

        Menu root = new Menu(null, null, "홈", "홈", "/", "00", 1);
        this.menuRepository.save(root);
        Menu system = new Menu(null, root.getMenuSeq(), "시스템", "시스템", "", "00", 1);
        this.menuRepository.save(system);
        Menu[] inSystem = {
                 new Menu(null, system.getMenuSeq(), "공통코드", "공통코드", "/commonCode", "00", 1)
                ,new Menu(null, system.getMenuSeq(), "메뉴관리", "메뉴관리", "", "00", 2)
        };
        this.menuRepository.saveAll(Arrays.asList(inSystem));
    }
}

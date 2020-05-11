package com.ysm.demo.init.database;

import com.ysm.demo.init.database.tables.COMMON_CODE;
import com.ysm.demo.init.database.tables.MENU;
import com.ysm.demo.init.database.tables.USER;
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

        COMMON_CODE[] commonCodes = {
                 new COMMON_CODE(new CommonCodePks("MenuType", "00"), "일반", 1)
                ,new COMMON_CODE(new CommonCodePks("MenuType", "10"), "팝업", 2)

                ,new COMMON_CODE(new CommonCodePks("AuthRole", "ADMIN"), "운영자", 1)
                ,new COMMON_CODE(new CommonCodePks("AuthRole", "MANAGER"), "관리자", 2)
                ,new COMMON_CODE(new CommonCodePks("AuthRole", "USER"), "일반", 3)
        };

        this.commonRepository.saveAll(Arrays.asList(commonCodes));

        //User
        this.userRepository.deleteAll();

        USER admin = new USER("admin", this.passwordEncoder.encode("1111"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        admin.setPasswordChangeDate(LocalDateTime.now().minusMonths(2));
        USER manager = new USER("manager", this.passwordEncoder.encode("1111"), "MANAGER", "ACCESS_TEST1");
        USER user = new USER("user", this.passwordEncoder.encode("1111"), "USER", "");
        List<USER> users = Arrays.asList(admin, manager, user);

        this.userRepository.saveAll(users);

        //Menu
        this.menuRepository.deleteAll();

        MENU root = new MENU(null, null, "홈", "홈", "/", "00", 1);
        this.menuRepository.save(root);
        MENU system = new MENU(null, root.getMenuSeq(), "시스템", "시스템", "", "00", 1);
        this.menuRepository.save(system);
        MENU[] inSystem = {
                new MENU(null, system.getMenuSeq(), "공통코드", "공통코드", "/system/commonCode", "00", 1)
               ,new MENU(null, system.getMenuSeq(), "메뉴설정", "메뉴설정", "", "00", 2)
               ,new MENU(null, system.getMenuSeq(), "회원설정", "회원설정", "", "00", 3)
        };
        this.menuRepository.saveAll(Arrays.asList(inSystem));
        MENU[] inMenu = {
                new MENU(null, inSystem[1].getMenuSeq(), "메뉴관리", "메뉴관리", "/system/menu/management", "00", 1)
               ,new MENU(null, inSystem[1].getMenuSeq(), "권한설정", "권한설정", "/system/menu/authority", "00", 2)
        };
        this.menuRepository.saveAll(Arrays.asList(inMenu));

        MENU[] inUser = {
                new MENU(null, inSystem[1].getMenuSeq(), "회원관리", "회원관리", "/system/user/management", "00", 1)
               ,new MENU(null, inSystem[1].getMenuSeq(), "권한설정", "권한설정", "/system/user/authority", "00", 2)
        };

        this.menuRepository.saveAll(Arrays.asList(inUser));
    }
}

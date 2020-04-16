package com.ysm.demo.config.principal;

public class UserPrincipalProperties {
    public static final String grantedPrefix = "ROLE_";

    public static final int loginFailCount = 5; // 5번 실패 시 잠김처리
    public static final int passwordChangeMonth = 6; //6개월마다 비밀번호 변경 필요

}

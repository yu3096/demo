package com.ysm.demo.init.database.tables;

import com.ysm.demo.init.database.tables.dataClasses.CorrectionInformation;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
public class USER extends CorrectionInformation{
  @Id
  @GeneratedValue(generator = "CustomUUID")
  @GenericGenerator(name = "CustomUUID", strategy = "com.ysm.demo.init.database.tables.dataClasses.CustomUUID")
  @Column(name = "USER_UUID", columnDefinition = "varchar(41)")
  private String userUuid;

  @Column(name = "USERNAME", nullable = false, length = 50)
  private String username;
  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "ROLES")
  private String roles = "";
  @Column(name = "PERMISSIONS")
  private String permissions = "";
  @Column(name = "ACTIVE_YN")
  @ColumnDefault("'Y'")
  private String activeYn;                  //활성화 여부

  @Column(name = "LOGIN_FAIL_CNT")
  @ColumnDefault("0")
  private int loginFailCnt;                 //로그인 실패횟수
  @Column(name = "EXPIRED_DATE")
  @ColumnDefault("'9999-12-31'")
  private LocalDateTime expiredDate;        //계정 만료일
  @Column(name = "PASSWORD_CHANGE_DATE")
  private LocalDateTime passwordChangeDate; //비밀번호 변경일

  @PrePersist
  private void setDefault(){
    this.activeYn = "Y";
    this.expiredDate = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    this.passwordChangeDate = LocalDateTime.now();//.minusMonths(3);
  }

  public USER(String username, String password, String roles, String permissions) {
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.permissions = permissions;
  }

  protected USER() {
  }

  public List<String> getRoleList() {
    if (this.roles.length() > 0) {
      return Arrays.asList(this.roles.split(","));
    } else {
      return new ArrayList<>();
    }
  }

  public List<String> getPermissionList() {
    if (this.permissions.length() > 0) {
      return Arrays.asList(this.permissions.split(","));
    } else {
      return new ArrayList<>();
    }
  }
}

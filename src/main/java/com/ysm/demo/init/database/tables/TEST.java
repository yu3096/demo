package com.ysm.demo.init.database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TEST {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String tmp1;
  @Column(nullable = false)
  private String tmp2;
  @Column(nullable = false)
  private String tmp3;
  @Column(nullable = false)
  private String tmp4;
  @Column(nullable = false)
  private String tmp5;
  @Column(nullable = false)
  private String tmp6;
}

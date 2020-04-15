package com.ysm.demo.init.database.tables.dataClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CommonCodePks implements Serializable {
  @Column(length = 10)
  private String commonGrp;
  @Column(length = 10)
  private String commonDtl;
}

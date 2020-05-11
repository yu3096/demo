package com.ysm.demo.init.database.tables.dataClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CommonCodePks implements Serializable {
  @Column(name = "COMMON_GRP", length = 10)
  private String commonGrp;
  @Column(name = "COMMON_DTL", length = 10)
  private String commonDtl;

  public CommonCodePks(String commonGrp, String commonDtl){
    this.commonGrp = commonGrp;
    this.commonDtl = commonDtl;
  }
}

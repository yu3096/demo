package com.ysm.demo.init.database.tables;

import com.ysm.demo.init.database.tables.dataClasses.CommonCodePks;
import com.ysm.demo.init.database.tables.dataClasses.CorrectionInformation;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommonCode extends CorrectionInformation {
  @EmbeddedId
  private CommonCodePks commonCodePks;

  private String description;

  @Column(nullable = false)
  private int orderNum;
}

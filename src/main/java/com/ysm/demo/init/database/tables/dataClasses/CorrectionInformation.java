package com.ysm.demo.init.database.tables.dataClasses;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
@Getter
@Setter
@MappedSuperclass
public abstract class CorrectionInformation {
  @CreationTimestamp
  private LocalDateTime createDate;
  private UUID createUser;

  @UpdateTimestamp
  private LocalDateTime updateDate;
  private UUID updateUser;
}

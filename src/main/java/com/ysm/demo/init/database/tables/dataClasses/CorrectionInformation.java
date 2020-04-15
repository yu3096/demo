package com.ysm.demo.init.database.tables.dataClasses;

import java.time.LocalDateTime;
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
  @AttributeOverride(name = "userUuid", column = @Column(name = "createUser"))
  private UserPks createUser;
  @UpdateTimestamp
  private LocalDateTime updateDate;
  @AttributeOverride(name = "userUuid", column = @Column(name = "updateUser"))
  private UserPks updateUser;
}

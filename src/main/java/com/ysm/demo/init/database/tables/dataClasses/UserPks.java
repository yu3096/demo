package com.ysm.demo.init.database.tables.dataClasses;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPks implements Serializable {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID userUuid;
}

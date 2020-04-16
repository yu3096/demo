package com.ysm.demo.init.database.tables.dataClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(
        name = "MENU_SEQ_GENERATOR"
        ,sequenceName = "MENU_SEQ"
        ,initialValue = 1
        ,allocationSize = 1
)
public class MenuPks implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                   ,generator = "MENU_SEQ_GENERATOR")
    public Long menuSeq;
}

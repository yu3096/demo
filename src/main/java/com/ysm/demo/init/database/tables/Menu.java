package com.ysm.demo.init.database.tables;

import com.ysm.demo.init.database.tables.dataClasses.CorrectionInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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
public class Menu extends CorrectionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                   ,generator = "MENU_SEQ_GENERATOR")
    public Long menuSeq;

    private Long parentMenuSeq;

    public String menuName;
    public String menuDesc;
    public String menuUri;
    public String menuType;

    @Column(nullable = false)
    public int orderNum;

}

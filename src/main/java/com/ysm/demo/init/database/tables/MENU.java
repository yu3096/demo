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
public class MENU extends CorrectionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                   ,generator = "MENU_SEQ_GENERATOR")
    @Column(name = "MENU_SEQ")
    public Long menuSeq;

    @Column(name = "PARENT_MENU_SEQ")
    private Long parentMenuSeq;

    @Column(name = "MENU_NAME")
    public String menuName;
    @Column(name = "MENU_DESC")
    public String menuDesc;
    @Column(name = "MENU_URI")
    public String menuUri;
    @Column(name = "MENU_TYPE")
    public String menuType;

    @Column(name = "ORDER_NUM", nullable = false)
    public int orderNum;

}

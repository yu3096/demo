package com.ysm.demo.init.database.tables;

import com.sun.istack.NotNull;
import com.ysm.demo.init.database.tables.dataClasses.CorrectionInformation;
import com.ysm.demo.init.database.tables.dataClasses.MenuPks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends CorrectionInformation {
    @EmbeddedId
    private MenuPks menuSeq;

    @Embedded
    private MenuPks parentMenuSeq;

    public String menuName;
    public String menuDesc;
    public String menuUri;
    public String menuType;

    @Column(nullable = false)
    public int orderNum;

}

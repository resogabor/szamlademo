package hu.rate.java.tanf.szamla.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by resog on 2017. 02. 20..
 */
@Entity
@Table(name = "szamla_tetel")
public class SzamlaTetel {
    @Id
    @GeneratedValue
    private Integer id;
    private String nev;
    private BigDecimal osszeg;



    //    @ManyToOne()
//    @JoinColumn(name="szamla_tetel_id")
//    private SzamlaFej szamlaFej;
    @Column(name = "szamla_tetel_id")
    private int szamlaTetelId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public BigDecimal getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(BigDecimal osszeg) {
        this.osszeg = osszeg;
    }

    public int getSzamlaTetelId() {
        return szamlaTetelId;
    }

    public void setSzamlaTetelId(int szamlaTetelId) {
        this.szamlaTetelId = szamlaTetelId;
    }
//    public SzamlaFej getSzamlaFej() {
//        return szamlaFej;
//    }
//
//    public void setSzamlaFej(SzamlaFej szamlaFej) {
//        this.szamlaFej = szamlaFej;
//    }
}

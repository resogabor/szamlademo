package hu.rate.java.tanf.szamla.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by resog on 2017. 02. 20..
 */
@Entity
@Table(name = "szamla_fej")
public class SzamlaFej {
    @Id
    @GeneratedValue
    private Integer id;
    private String vevo;
    private String elado;
    private BigDecimal summ;
    @OneToMany()
    @JoinColumn(name = "szamla_tetel_id", referencedColumnName = "id")
    private List<SzamlaTetel> szamlaTetelek;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVevo() {
        return vevo;
    }

    public void setVevo(String vevo) {
        this.vevo = vevo;
    }

    public String getElado() {
        return elado;
    }

    public void setElado(String elado) {
        this.elado = elado;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public List<SzamlaTetel> getSzamlaTetelek() {
        return szamlaTetelek;
    }

    public void setSzamlaTetelek(List<SzamlaTetel> szamlaTetelek) {
        this.szamlaTetelek = szamlaTetelek;
    }
}

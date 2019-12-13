package com.bbc.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Stoc.
 */
@Entity
@Table(name = "stoc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "htrocarno", nullable = false)
    private Integer htrocarno;

    @NotNull
    @Column(name = "resdealerid", nullable = false)
    private Integer resdealerid;

    @Column(name = "anfabricatieciv")
    private Integer anfabricatieciv;

    @Column(name = "tipautovehicul")
    private String tipautovehicul;

    @Column(name = "codculoareexterior")
    private String codculoareexterior;

    @Column(name = "descculoareexterior")
    private String descculoareexterior;

    @Column(name = "vopseametalizata")
    private String vopseametalizata;

    @Column(name = "culoareinterior")
    private String culoareinterior;

    @Column(name = "observatii")
    private String observatii;

    @Column(name = "locatie")
    private String locatie;

    @Column(name = "omologareind")
    private String omologareind;

    @Column(name = "lunasosireintara")
    private String lunasosireintara;

    @Column(name = "rezervata")
    private String rezervata;

    @Column(name = "dataexpirarerez")
    private String dataexpirarerez;

    @OneToMany(mappedBy = "stoc")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Dealer> dealers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHtrocarno() {
        return htrocarno;
    }

    public Stoc htrocarno(Integer htrocarno) {
        this.htrocarno = htrocarno;
        return this;
    }

    public void setHtrocarno(Integer htrocarno) {
        this.htrocarno = htrocarno;
    }

    public Integer getResdealerid() {
        return resdealerid;
    }

    public Stoc resdealerid(Integer resdealerid) {
        this.resdealerid = resdealerid;
        return this;
    }

    public void setResdealerid(Integer resdealerid) {
        this.resdealerid = resdealerid;
    }

    public Integer getAnfabricatieciv() {
        return anfabricatieciv;
    }

    public Stoc anfabricatieciv(Integer anfabricatieciv) {
        this.anfabricatieciv = anfabricatieciv;
        return this;
    }

    public void setAnfabricatieciv(Integer anfabricatieciv) {
        this.anfabricatieciv = anfabricatieciv;
    }

    public String getTipautovehicul() {
        return tipautovehicul;
    }

    public Stoc tipautovehicul(String tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
        return this;
    }

    public void setTipautovehicul(String tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
    }

    public String getCodculoareexterior() {
        return codculoareexterior;
    }

    public Stoc codculoareexterior(String codculoareexterior) {
        this.codculoareexterior = codculoareexterior;
        return this;
    }

    public void setCodculoareexterior(String codculoareexterior) {
        this.codculoareexterior = codculoareexterior;
    }

    public String getDescculoareexterior() {
        return descculoareexterior;
    }

    public Stoc descculoareexterior(String descculoareexterior) {
        this.descculoareexterior = descculoareexterior;
        return this;
    }

    public void setDescculoareexterior(String descculoareexterior) {
        this.descculoareexterior = descculoareexterior;
    }

    public String getVopseametalizata() {
        return vopseametalizata;
    }

    public Stoc vopseametalizata(String vopseametalizata) {
        this.vopseametalizata = vopseametalizata;
        return this;
    }

    public void setVopseametalizata(String vopseametalizata) {
        this.vopseametalizata = vopseametalizata;
    }

    public String getCuloareinterior() {
        return culoareinterior;
    }

    public Stoc culoareinterior(String culoareinterior) {
        this.culoareinterior = culoareinterior;
        return this;
    }

    public void setCuloareinterior(String culoareinterior) {
        this.culoareinterior = culoareinterior;
    }

    public String getObservatii() {
        return observatii;
    }

    public Stoc observatii(String observatii) {
        this.observatii = observatii;
        return this;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public String getLocatie() {
        return locatie;
    }

    public Stoc locatie(String locatie) {
        this.locatie = locatie;
        return this;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getOmologareind() {
        return omologareind;
    }

    public Stoc omologareind(String omologareind) {
        this.omologareind = omologareind;
        return this;
    }

    public void setOmologareind(String omologareind) {
        this.omologareind = omologareind;
    }

    public String getLunasosireintara() {
        return lunasosireintara;
    }

    public Stoc lunasosireintara(String lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
        return this;
    }

    public void setLunasosireintara(String lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
    }

    public String getRezervata() {
        return rezervata;
    }

    public Stoc rezervata(String rezervata) {
        this.rezervata = rezervata;
        return this;
    }

    public void setRezervata(String rezervata) {
        this.rezervata = rezervata;
    }

    public String getDataexpirarerez() {
        return dataexpirarerez;
    }

    public Stoc dataexpirarerez(String dataexpirarerez) {
        this.dataexpirarerez = dataexpirarerez;
        return this;
    }

    public void setDataexpirarerez(String dataexpirarerez) {
        this.dataexpirarerez = dataexpirarerez;
    }

    public Set<Dealer> getDealers() {
        return dealers;
    }

    public Stoc dealers(Set<Dealer> dealers) {
        this.dealers = dealers;
        return this;
    }

    public Stoc addDealer(Dealer dealer) {
        this.dealers.add(dealer);
        dealer.setStoc(this);
        return this;
    }

    public Stoc removeDealer(Dealer dealer) {
        this.dealers.remove(dealer);
        dealer.setStoc(null);
        return this;
    }

    public void setDealers(Set<Dealer> dealers) {
        this.dealers = dealers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stoc)) {
            return false;
        }
        return id != null && id.equals(((Stoc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Stoc{" +
            "id=" + getId() +
            ", htrocarno=" + getHtrocarno() +
            ", resdealerid=" + getResdealerid() +
            ", anfabricatieciv=" + getAnfabricatieciv() +
            ", tipautovehicul='" + getTipautovehicul() + "'" +
            ", codculoareexterior='" + getCodculoareexterior() + "'" +
            ", descculoareexterior='" + getDescculoareexterior() + "'" +
            ", vopseametalizata='" + getVopseametalizata() + "'" +
            ", culoareinterior='" + getCuloareinterior() + "'" +
            ", observatii='" + getObservatii() + "'" +
            ", locatie='" + getLocatie() + "'" +
            ", omologareind='" + getOmologareind() + "'" +
            ", lunasosireintara='" + getLunasosireintara() + "'" +
            ", rezervata='" + getRezervata() + "'" +
            ", dataexpirarerez='" + getDataexpirarerez() + "'" +
            "}";
    }
}

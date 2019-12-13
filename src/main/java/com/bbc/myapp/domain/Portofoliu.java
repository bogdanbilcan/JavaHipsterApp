package com.bbc.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Portofoliu.
 */
@Entity
@Table(name = "portofoliu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Portofoliu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "htrocarno", nullable = false)
    private Integer htrocarno;

    @NotNull
    @Column(name = "dealer", nullable = false)
    private String dealer;

    @Column(name = "datarezsaufactura")
    private String datarezsaufactura;

    @Column(name = "dataexpirare")
    private String dataexpirare;

    @NotNull
    @Column(name = "resdealerid", nullable = false)
    private Integer resdealerid;

    @Column(name = "tiplinie")
    private String tiplinie;

    @Column(name = "locatie")
    private String locatie;

    @Column(name = "lunaproductie")
    private String lunaproductie;

    @Column(name = "lunasosireintara")
    private String lunasosireintara;

    @Column(name = "codmodel")
    private String codmodel;

    @Column(name = "tipautovehicul")
    private String tipautovehicul;

    @Column(name = "codculoareext")
    private String codculoareext;

    @Column(name = "culoareexterior")
    private String culoareexterior;

    @Column(name = "culoare_integererior")
    private String culoareIntegererior;

    @Column(name = "observatii")
    private String observatii;

    @Column(name = "numeclient")
    private String numeclient;

    @Column(name = "numevanzator")
    private String numevanzator;

    @Column(name = "vin")
    private String vin;

    @Column(name = "engineno")
    private String engineno;

    @Column(name = "anfabricatiecfciv")
    private Integer anfabricatiecfciv;

    @Column(name = "omologareindividuala")
    private String omologareindividuala;

    @Column(name = "pretlista")
    private Integer pretlista;

    @Column(name = "discountstandard")
    private Integer discountstandard;

    @Column(name = "discountsuplimentar")
    private Integer discountsuplimentar;

    @Column(name = "trusalegislativa")
    private Integer trusalegislativa;

    @Column(name = "pretfinal")
    private Integer pretfinal;

    @Column(name = "avansplatit")
    private Integer avansplatit;

    @Column(name = "restdeplata")
    private Integer restdeplata;

    @Column(name = "customertrxid")
    private Integer customertrxid;

    @Column(name = "rezcustid")
    private String rezcustid;

    @Column(name = "soldcustid")
    private Integer soldcustid;

    @Column(name = "proforma")
    private Boolean proforma;

    @Column(name = "transport")
    private Boolean transport;

    @OneToMany(mappedBy = "portofoliu")
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

    public Portofoliu htrocarno(Integer htrocarno) {
        this.htrocarno = htrocarno;
        return this;
    }

    public void setHtrocarno(Integer htrocarno) {
        this.htrocarno = htrocarno;
    }

    public String getDealer() {
        return dealer;
    }

    public Portofoliu dealer(String dealer) {
        this.dealer = dealer;
        return this;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getDatarezsaufactura() {
        return datarezsaufactura;
    }

    public Portofoliu datarezsaufactura(String datarezsaufactura) {
        this.datarezsaufactura = datarezsaufactura;
        return this;
    }

    public void setDatarezsaufactura(String datarezsaufactura) {
        this.datarezsaufactura = datarezsaufactura;
    }

    public String getDataexpirare() {
        return dataexpirare;
    }

    public Portofoliu dataexpirare(String dataexpirare) {
        this.dataexpirare = dataexpirare;
        return this;
    }

    public void setDataexpirare(String dataexpirare) {
        this.dataexpirare = dataexpirare;
    }

    public Integer getResdealerid() {
        return resdealerid;
    }

    public Portofoliu resdealerid(Integer resdealerid) {
        this.resdealerid = resdealerid;
        return this;
    }

    public void setResdealerid(Integer resdealerid) {
        this.resdealerid = resdealerid;
    }

    public String getTiplinie() {
        return tiplinie;
    }

    public Portofoliu tiplinie(String tiplinie) {
        this.tiplinie = tiplinie;
        return this;
    }

    public void setTiplinie(String tiplinie) {
        this.tiplinie = tiplinie;
    }

    public String getLocatie() {
        return locatie;
    }

    public Portofoliu locatie(String locatie) {
        this.locatie = locatie;
        return this;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getLunaproductie() {
        return lunaproductie;
    }

    public Portofoliu lunaproductie(String lunaproductie) {
        this.lunaproductie = lunaproductie;
        return this;
    }

    public void setLunaproductie(String lunaproductie) {
        this.lunaproductie = lunaproductie;
    }

    public String getLunasosireintara() {
        return lunasosireintara;
    }

    public Portofoliu lunasosireintara(String lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
        return this;
    }

    public void setLunasosireintara(String lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
    }

    public String getCodmodel() {
        return codmodel;
    }

    public Portofoliu codmodel(String codmodel) {
        this.codmodel = codmodel;
        return this;
    }

    public void setCodmodel(String codmodel) {
        this.codmodel = codmodel;
    }

    public String getTipautovehicul() {
        return tipautovehicul;
    }

    public Portofoliu tipautovehicul(String tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
        return this;
    }

    public void setTipautovehicul(String tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
    }

    public String getCodculoareext() {
        return codculoareext;
    }

    public Portofoliu codculoareext(String codculoareext) {
        this.codculoareext = codculoareext;
        return this;
    }

    public void setCodculoareext(String codculoareext) {
        this.codculoareext = codculoareext;
    }

    public String getCuloareexterior() {
        return culoareexterior;
    }

    public Portofoliu culoareexterior(String culoareexterior) {
        this.culoareexterior = culoareexterior;
        return this;
    }

    public void setCuloareexterior(String culoareexterior) {
        this.culoareexterior = culoareexterior;
    }

    public String getCuloareIntegererior() {
        return culoareIntegererior;
    }

    public Portofoliu culoareIntegererior(String culoareIntegererior) {
        this.culoareIntegererior = culoareIntegererior;
        return this;
    }

    public void setCuloareIntegererior(String culoareIntegererior) {
        this.culoareIntegererior = culoareIntegererior;
    }

    public String getObservatii() {
        return observatii;
    }

    public Portofoliu observatii(String observatii) {
        this.observatii = observatii;
        return this;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public String getNumeclient() {
        return numeclient;
    }

    public Portofoliu numeclient(String numeclient) {
        this.numeclient = numeclient;
        return this;
    }

    public void setNumeclient(String numeclient) {
        this.numeclient = numeclient;
    }

    public String getNumevanzator() {
        return numevanzator;
    }

    public Portofoliu numevanzator(String numevanzator) {
        this.numevanzator = numevanzator;
        return this;
    }

    public void setNumevanzator(String numevanzator) {
        this.numevanzator = numevanzator;
    }

    public String getVin() {
        return vin;
    }

    public Portofoliu vin(String vin) {
        this.vin = vin;
        return this;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineno() {
        return engineno;
    }

    public Portofoliu engineno(String engineno) {
        this.engineno = engineno;
        return this;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public Integer getAnfabricatiecfciv() {
        return anfabricatiecfciv;
    }

    public Portofoliu anfabricatiecfciv(Integer anfabricatiecfciv) {
        this.anfabricatiecfciv = anfabricatiecfciv;
        return this;
    }

    public void setAnfabricatiecfciv(Integer anfabricatiecfciv) {
        this.anfabricatiecfciv = anfabricatiecfciv;
    }

    public String getOmologareindividuala() {
        return omologareindividuala;
    }

    public Portofoliu omologareindividuala(String omologareindividuala) {
        this.omologareindividuala = omologareindividuala;
        return this;
    }

    public void setOmologareindividuala(String omologareindividuala) {
        this.omologareindividuala = omologareindividuala;
    }

    public Integer getPretlista() {
        return pretlista;
    }

    public Portofoliu pretlista(Integer pretlista) {
        this.pretlista = pretlista;
        return this;
    }

    public void setPretlista(Integer pretlista) {
        this.pretlista = pretlista;
    }

    public Integer getDiscountstandard() {
        return discountstandard;
    }

    public Portofoliu discountstandard(Integer discountstandard) {
        this.discountstandard = discountstandard;
        return this;
    }

    public void setDiscountstandard(Integer discountstandard) {
        this.discountstandard = discountstandard;
    }

    public Integer getDiscountsuplimentar() {
        return discountsuplimentar;
    }

    public Portofoliu discountsuplimentar(Integer discountsuplimentar) {
        this.discountsuplimentar = discountsuplimentar;
        return this;
    }

    public void setDiscountsuplimentar(Integer discountsuplimentar) {
        this.discountsuplimentar = discountsuplimentar;
    }

    public Integer getTrusalegislativa() {
        return trusalegislativa;
    }

    public Portofoliu trusalegislativa(Integer trusalegislativa) {
        this.trusalegislativa = trusalegislativa;
        return this;
    }

    public void setTrusalegislativa(Integer trusalegislativa) {
        this.trusalegislativa = trusalegislativa;
    }

    public Integer getPretfinal() {
        return pretfinal;
    }

    public Portofoliu pretfinal(Integer pretfinal) {
        this.pretfinal = pretfinal;
        return this;
    }

    public void setPretfinal(Integer pretfinal) {
        this.pretfinal = pretfinal;
    }

    public Integer getAvansplatit() {
        return avansplatit;
    }

    public Portofoliu avansplatit(Integer avansplatit) {
        this.avansplatit = avansplatit;
        return this;
    }

    public void setAvansplatit(Integer avansplatit) {
        this.avansplatit = avansplatit;
    }

    public Integer getRestdeplata() {
        return restdeplata;
    }

    public Portofoliu restdeplata(Integer restdeplata) {
        this.restdeplata = restdeplata;
        return this;
    }

    public void setRestdeplata(Integer restdeplata) {
        this.restdeplata = restdeplata;
    }

    public Integer getCustomertrxid() {
        return customertrxid;
    }

    public Portofoliu customertrxid(Integer customertrxid) {
        this.customertrxid = customertrxid;
        return this;
    }

    public void setCustomertrxid(Integer customertrxid) {
        this.customertrxid = customertrxid;
    }

    public String getRezcustid() {
        return rezcustid;
    }

    public Portofoliu rezcustid(String rezcustid) {
        this.rezcustid = rezcustid;
        return this;
    }

    public void setRezcustid(String rezcustid) {
        this.rezcustid = rezcustid;
    }

    public Integer getSoldcustid() {
        return soldcustid;
    }

    public Portofoliu soldcustid(Integer soldcustid) {
        this.soldcustid = soldcustid;
        return this;
    }

    public void setSoldcustid(Integer soldcustid) {
        this.soldcustid = soldcustid;
    }

    public Boolean isProforma() {
        return proforma;
    }

    public Portofoliu proforma(Boolean proforma) {
        this.proforma = proforma;
        return this;
    }

    public void setProforma(Boolean proforma) {
        this.proforma = proforma;
    }

    public Boolean isTransport() {
        return transport;
    }

    public Portofoliu transport(Boolean transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Boolean transport) {
        this.transport = transport;
    }

    public Set<Dealer> getDealers() {
        return dealers;
    }

    public Portofoliu dealers(Set<Dealer> dealers) {
        this.dealers = dealers;
        return this;
    }

    public Portofoliu addDealer(Dealer dealer) {
        this.dealers.add(dealer);
        dealer.setPortofoliu(this);
        return this;
    }

    public Portofoliu removeDealer(Dealer dealer) {
        this.dealers.remove(dealer);
        dealer.setPortofoliu(null);
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
        if (!(o instanceof Portofoliu)) {
            return false;
        }
        return id != null && id.equals(((Portofoliu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Portofoliu{" +
            "id=" + getId() +
            ", htrocarno=" + getHtrocarno() +
            ", dealer='" + getDealer() + "'" +
            ", datarezsaufactura='" + getDatarezsaufactura() + "'" +
            ", dataexpirare='" + getDataexpirare() + "'" +
            ", resdealerid=" + getResdealerid() +
            ", tiplinie='" + getTiplinie() + "'" +
            ", locatie='" + getLocatie() + "'" +
            ", lunaproductie='" + getLunaproductie() + "'" +
            ", lunasosireintara='" + getLunasosireintara() + "'" +
            ", codmodel='" + getCodmodel() + "'" +
            ", tipautovehicul='" + getTipautovehicul() + "'" +
            ", codculoareext='" + getCodculoareext() + "'" +
            ", culoareexterior='" + getCuloareexterior() + "'" +
            ", culoareIntegererior='" + getCuloareIntegererior() + "'" +
            ", observatii='" + getObservatii() + "'" +
            ", numeclient='" + getNumeclient() + "'" +
            ", numevanzator='" + getNumevanzator() + "'" +
            ", vin='" + getVin() + "'" +
            ", engineno='" + getEngineno() + "'" +
            ", anfabricatiecfciv=" + getAnfabricatiecfciv() +
            ", omologareindividuala='" + getOmologareindividuala() + "'" +
            ", pretlista=" + getPretlista() +
            ", discountstandard=" + getDiscountstandard() +
            ", discountsuplimentar=" + getDiscountsuplimentar() +
            ", trusalegislativa=" + getTrusalegislativa() +
            ", pretfinal=" + getPretfinal() +
            ", avansplatit=" + getAvansplatit() +
            ", restdeplata=" + getRestdeplata() +
            ", customertrxid=" + getCustomertrxid() +
            ", rezcustid='" + getRezcustid() + "'" +
            ", soldcustid=" + getSoldcustid() +
            ", proforma='" + isProforma() + "'" +
            ", transport='" + isTransport() + "'" +
            "}";
    }
}

package com.bbc.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bbc.myapp.domain.Portofoliu} entity.
 */
public class PortofoliuDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer htrocarno;

    @NotNull
    private String dealer;

    private String datarezsaufactura;

    private String dataexpirare;

    @NotNull
    private Integer resdealerid;

    private String tiplinie;

    private String locatie;

    private String lunaproductie;

    private String lunasosireintara;

    private String codmodel;

    private String tipautovehicul;

    private String codculoareext;

    private String culoareexterior;

    private String culoareIntegererior;

    private String observatii;

    private String numeclient;

    private String numevanzator;

    private String vin;

    private String engineno;

    private Integer anfabricatiecfciv;

    private String omologareindividuala;

    private Integer pretlista;

    private Integer discountstandard;

    private Integer discountsuplimentar;

    private Integer trusalegislativa;

    private Integer pretfinal;

    private Integer avansplatit;

    private Integer restdeplata;

    private Integer customertrxid;

    private String rezcustid;

    private Integer soldcustid;

    private Boolean proforma;

    private Boolean transport;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHtrocarno() {
        return htrocarno;
    }

    public void setHtrocarno(Integer htrocarno) {
        this.htrocarno = htrocarno;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getDatarezsaufactura() {
        return datarezsaufactura;
    }

    public void setDatarezsaufactura(String datarezsaufactura) {
        this.datarezsaufactura = datarezsaufactura;
    }

    public String getDataexpirare() {
        return dataexpirare;
    }

    public void setDataexpirare(String dataexpirare) {
        this.dataexpirare = dataexpirare;
    }

    public Integer getResdealerid() {
        return resdealerid;
    }

    public void setResdealerid(Integer resdealerid) {
        this.resdealerid = resdealerid;
    }

    public String getTiplinie() {
        return tiplinie;
    }

    public void setTiplinie(String tiplinie) {
        this.tiplinie = tiplinie;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getLunaproductie() {
        return lunaproductie;
    }

    public void setLunaproductie(String lunaproductie) {
        this.lunaproductie = lunaproductie;
    }

    public String getLunasosireintara() {
        return lunasosireintara;
    }

    public void setLunasosireintara(String lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
    }

    public String getCodmodel() {
        return codmodel;
    }

    public void setCodmodel(String codmodel) {
        this.codmodel = codmodel;
    }

    public String getTipautovehicul() {
        return tipautovehicul;
    }

    public void setTipautovehicul(String tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
    }

    public String getCodculoareext() {
        return codculoareext;
    }

    public void setCodculoareext(String codculoareext) {
        this.codculoareext = codculoareext;
    }

    public String getCuloareexterior() {
        return culoareexterior;
    }

    public void setCuloareexterior(String culoareexterior) {
        this.culoareexterior = culoareexterior;
    }

    public String getCuloareIntegererior() {
        return culoareIntegererior;
    }

    public void setCuloareIntegererior(String culoareIntegererior) {
        this.culoareIntegererior = culoareIntegererior;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public String getNumeclient() {
        return numeclient;
    }

    public void setNumeclient(String numeclient) {
        this.numeclient = numeclient;
    }

    public String getNumevanzator() {
        return numevanzator;
    }

    public void setNumevanzator(String numevanzator) {
        this.numevanzator = numevanzator;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public Integer getAnfabricatiecfciv() {
        return anfabricatiecfciv;
    }

    public void setAnfabricatiecfciv(Integer anfabricatiecfciv) {
        this.anfabricatiecfciv = anfabricatiecfciv;
    }

    public String getOmologareindividuala() {
        return omologareindividuala;
    }

    public void setOmologareindividuala(String omologareindividuala) {
        this.omologareindividuala = omologareindividuala;
    }

    public Integer getPretlista() {
        return pretlista;
    }

    public void setPretlista(Integer pretlista) {
        this.pretlista = pretlista;
    }

    public Integer getDiscountstandard() {
        return discountstandard;
    }

    public void setDiscountstandard(Integer discountstandard) {
        this.discountstandard = discountstandard;
    }

    public Integer getDiscountsuplimentar() {
        return discountsuplimentar;
    }

    public void setDiscountsuplimentar(Integer discountsuplimentar) {
        this.discountsuplimentar = discountsuplimentar;
    }

    public Integer getTrusalegislativa() {
        return trusalegislativa;
    }

    public void setTrusalegislativa(Integer trusalegislativa) {
        this.trusalegislativa = trusalegislativa;
    }

    public Integer getPretfinal() {
        return pretfinal;
    }

    public void setPretfinal(Integer pretfinal) {
        this.pretfinal = pretfinal;
    }

    public Integer getAvansplatit() {
        return avansplatit;
    }

    public void setAvansplatit(Integer avansplatit) {
        this.avansplatit = avansplatit;
    }

    public Integer getRestdeplata() {
        return restdeplata;
    }

    public void setRestdeplata(Integer restdeplata) {
        this.restdeplata = restdeplata;
    }

    public Integer getCustomertrxid() {
        return customertrxid;
    }

    public void setCustomertrxid(Integer customertrxid) {
        this.customertrxid = customertrxid;
    }

    public String getRezcustid() {
        return rezcustid;
    }

    public void setRezcustid(String rezcustid) {
        this.rezcustid = rezcustid;
    }

    public Integer getSoldcustid() {
        return soldcustid;
    }

    public void setSoldcustid(Integer soldcustid) {
        this.soldcustid = soldcustid;
    }

    public Boolean isProforma() {
        return proforma;
    }

    public void setProforma(Boolean proforma) {
        this.proforma = proforma;
    }

    public Boolean isTransport() {
        return transport;
    }

    public void setTransport(Boolean transport) {
        this.transport = transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PortofoliuDTO portofoliuDTO = (PortofoliuDTO) o;
        if (portofoliuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), portofoliuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PortofoliuDTO{" +
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

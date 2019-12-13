package com.bbc.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bbc.myapp.domain.Stoc} entity.
 */
public class StocDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer htrocarno;

    @NotNull
    private Integer resdealerid;

    private Integer anfabricatieciv;

    private String tipautovehicul;

    private String codculoareexterior;

    private String descculoareexterior;

    private String vopseametalizata;

    private String culoareinterior;

    private String observatii;

    private String locatie;

    private String omologareind;

    private String lunasosireintara;

    private String rezervata;

    private String dataexpirarerez;


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

    public Integer getResdealerid() {
        return resdealerid;
    }

    public void setResdealerid(Integer resdealerid) {
        this.resdealerid = resdealerid;
    }

    public Integer getAnfabricatieciv() {
        return anfabricatieciv;
    }

    public void setAnfabricatieciv(Integer anfabricatieciv) {
        this.anfabricatieciv = anfabricatieciv;
    }

    public String getTipautovehicul() {
        return tipautovehicul;
    }

    public void setTipautovehicul(String tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
    }

    public String getCodculoareexterior() {
        return codculoareexterior;
    }

    public void setCodculoareexterior(String codculoareexterior) {
        this.codculoareexterior = codculoareexterior;
    }

    public String getDescculoareexterior() {
        return descculoareexterior;
    }

    public void setDescculoareexterior(String descculoareexterior) {
        this.descculoareexterior = descculoareexterior;
    }

    public String getVopseametalizata() {
        return vopseametalizata;
    }

    public void setVopseametalizata(String vopseametalizata) {
        this.vopseametalizata = vopseametalizata;
    }

    public String getCuloareinterior() {
        return culoareinterior;
    }

    public void setCuloareinterior(String culoareinterior) {
        this.culoareinterior = culoareinterior;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getOmologareind() {
        return omologareind;
    }

    public void setOmologareind(String omologareind) {
        this.omologareind = omologareind;
    }

    public String getLunasosireintara() {
        return lunasosireintara;
    }

    public void setLunasosireintara(String lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
    }

    public String getRezervata() {
        return rezervata;
    }

    public void setRezervata(String rezervata) {
        this.rezervata = rezervata;
    }

    public String getDataexpirarerez() {
        return dataexpirarerez;
    }

    public void setDataexpirarerez(String dataexpirarerez) {
        this.dataexpirarerez = dataexpirarerez;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StocDTO stocDTO = (StocDTO) o;
        if (stocDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stocDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StocDTO{" +
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

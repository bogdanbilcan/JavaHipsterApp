package com.bbc.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bbc.myapp.domain.Stoc} entity. This class is used
 * in {@link com.bbc.myapp.web.rest.StocResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /stocs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StocCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter htrocarno;

    private IntegerFilter resdealerid;

    private IntegerFilter anfabricatieciv;

    private StringFilter tipautovehicul;

    private StringFilter codculoareexterior;

    private StringFilter descculoareexterior;

    private StringFilter vopseametalizata;

    private StringFilter culoareinterior;

    private StringFilter observatii;

    private StringFilter locatie;

    private StringFilter omologareind;

    private StringFilter lunasosireintara;

    private StringFilter rezervata;

    private StringFilter dataexpirarerez;

    private LongFilter dealerId;

    public StocCriteria(){
    }

    public StocCriteria(StocCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.htrocarno = other.htrocarno == null ? null : other.htrocarno.copy();
        this.resdealerid = other.resdealerid == null ? null : other.resdealerid.copy();
        this.anfabricatieciv = other.anfabricatieciv == null ? null : other.anfabricatieciv.copy();
        this.tipautovehicul = other.tipautovehicul == null ? null : other.tipautovehicul.copy();
        this.codculoareexterior = other.codculoareexterior == null ? null : other.codculoareexterior.copy();
        this.descculoareexterior = other.descculoareexterior == null ? null : other.descculoareexterior.copy();
        this.vopseametalizata = other.vopseametalizata == null ? null : other.vopseametalizata.copy();
        this.culoareinterior = other.culoareinterior == null ? null : other.culoareinterior.copy();
        this.observatii = other.observatii == null ? null : other.observatii.copy();
        this.locatie = other.locatie == null ? null : other.locatie.copy();
        this.omologareind = other.omologareind == null ? null : other.omologareind.copy();
        this.lunasosireintara = other.lunasosireintara == null ? null : other.lunasosireintara.copy();
        this.rezervata = other.rezervata == null ? null : other.rezervata.copy();
        this.dataexpirarerez = other.dataexpirarerez == null ? null : other.dataexpirarerez.copy();
        this.dealerId = other.dealerId == null ? null : other.dealerId.copy();
    }

    @Override
    public StocCriteria copy() {
        return new StocCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getHtrocarno() {
        return htrocarno;
    }

    public void setHtrocarno(IntegerFilter htrocarno) {
        this.htrocarno = htrocarno;
    }

    public IntegerFilter getResdealerid() {
        return resdealerid;
    }

    public void setResdealerid(IntegerFilter resdealerid) {
        this.resdealerid = resdealerid;
    }

    public IntegerFilter getAnfabricatieciv() {
        return anfabricatieciv;
    }

    public void setAnfabricatieciv(IntegerFilter anfabricatieciv) {
        this.anfabricatieciv = anfabricatieciv;
    }

    public StringFilter getTipautovehicul() {
        return tipautovehicul;
    }

    public void setTipautovehicul(StringFilter tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
    }

    public StringFilter getCodculoareexterior() {
        return codculoareexterior;
    }

    public void setCodculoareexterior(StringFilter codculoareexterior) {
        this.codculoareexterior = codculoareexterior;
    }

    public StringFilter getDescculoareexterior() {
        return descculoareexterior;
    }

    public void setDescculoareexterior(StringFilter descculoareexterior) {
        this.descculoareexterior = descculoareexterior;
    }

    public StringFilter getVopseametalizata() {
        return vopseametalizata;
    }

    public void setVopseametalizata(StringFilter vopseametalizata) {
        this.vopseametalizata = vopseametalizata;
    }

    public StringFilter getCuloareinterior() {
        return culoareinterior;
    }

    public void setCuloareinterior(StringFilter culoareinterior) {
        this.culoareinterior = culoareinterior;
    }

    public StringFilter getObservatii() {
        return observatii;
    }

    public void setObservatii(StringFilter observatii) {
        this.observatii = observatii;
    }

    public StringFilter getLocatie() {
        return locatie;
    }

    public void setLocatie(StringFilter locatie) {
        this.locatie = locatie;
    }

    public StringFilter getOmologareind() {
        return omologareind;
    }

    public void setOmologareind(StringFilter omologareind) {
        this.omologareind = omologareind;
    }

    public StringFilter getLunasosireintara() {
        return lunasosireintara;
    }

    public void setLunasosireintara(StringFilter lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
    }

    public StringFilter getRezervata() {
        return rezervata;
    }

    public void setRezervata(StringFilter rezervata) {
        this.rezervata = rezervata;
    }

    public StringFilter getDataexpirarerez() {
        return dataexpirarerez;
    }

    public void setDataexpirarerez(StringFilter dataexpirarerez) {
        this.dataexpirarerez = dataexpirarerez;
    }

    public LongFilter getDealerId() {
        return dealerId;
    }

    public void setDealerId(LongFilter dealerId) {
        this.dealerId = dealerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StocCriteria that = (StocCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(htrocarno, that.htrocarno) &&
            Objects.equals(resdealerid, that.resdealerid) &&
            Objects.equals(anfabricatieciv, that.anfabricatieciv) &&
            Objects.equals(tipautovehicul, that.tipautovehicul) &&
            Objects.equals(codculoareexterior, that.codculoareexterior) &&
            Objects.equals(descculoareexterior, that.descculoareexterior) &&
            Objects.equals(vopseametalizata, that.vopseametalizata) &&
            Objects.equals(culoareinterior, that.culoareinterior) &&
            Objects.equals(observatii, that.observatii) &&
            Objects.equals(locatie, that.locatie) &&
            Objects.equals(omologareind, that.omologareind) &&
            Objects.equals(lunasosireintara, that.lunasosireintara) &&
            Objects.equals(rezervata, that.rezervata) &&
            Objects.equals(dataexpirarerez, that.dataexpirarerez) &&
            Objects.equals(dealerId, that.dealerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        htrocarno,
        resdealerid,
        anfabricatieciv,
        tipautovehicul,
        codculoareexterior,
        descculoareexterior,
        vopseametalizata,
        culoareinterior,
        observatii,
        locatie,
        omologareind,
        lunasosireintara,
        rezervata,
        dataexpirarerez,
        dealerId
        );
    }

    @Override
    public String toString() {
        return "StocCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (htrocarno != null ? "htrocarno=" + htrocarno + ", " : "") +
                (resdealerid != null ? "resdealerid=" + resdealerid + ", " : "") +
                (anfabricatieciv != null ? "anfabricatieciv=" + anfabricatieciv + ", " : "") +
                (tipautovehicul != null ? "tipautovehicul=" + tipautovehicul + ", " : "") +
                (codculoareexterior != null ? "codculoareexterior=" + codculoareexterior + ", " : "") +
                (descculoareexterior != null ? "descculoareexterior=" + descculoareexterior + ", " : "") +
                (vopseametalizata != null ? "vopseametalizata=" + vopseametalizata + ", " : "") +
                (culoareinterior != null ? "culoareinterior=" + culoareinterior + ", " : "") +
                (observatii != null ? "observatii=" + observatii + ", " : "") +
                (locatie != null ? "locatie=" + locatie + ", " : "") +
                (omologareind != null ? "omologareind=" + omologareind + ", " : "") +
                (lunasosireintara != null ? "lunasosireintara=" + lunasosireintara + ", " : "") +
                (rezervata != null ? "rezervata=" + rezervata + ", " : "") +
                (dataexpirarerez != null ? "dataexpirarerez=" + dataexpirarerez + ", " : "") +
                (dealerId != null ? "dealerId=" + dealerId + ", " : "") +
            "}";
    }

}

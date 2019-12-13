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
 * Criteria class for the {@link com.bbc.myapp.domain.Portofoliu} entity. This class is used
 * in {@link com.bbc.myapp.web.rest.PortofoliuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /portofolius?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PortofoliuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter htrocarno;

    private StringFilter dealer;

    private StringFilter datarezsaufactura;

    private StringFilter dataexpirare;

    private IntegerFilter resdealerid;

    private StringFilter tiplinie;

    private StringFilter locatie;

    private StringFilter lunaproductie;

    private StringFilter lunasosireintara;

    private StringFilter codmodel;

    private StringFilter tipautovehicul;

    private StringFilter codculoareext;

    private StringFilter culoareexterior;

    private StringFilter culoareIntegererior;

    private StringFilter observatii;

    private StringFilter numeclient;

    private StringFilter numevanzator;

    private StringFilter vin;

    private StringFilter engineno;

    private IntegerFilter anfabricatiecfciv;

    private StringFilter omologareindividuala;

    private IntegerFilter pretlista;

    private IntegerFilter discountstandard;

    private IntegerFilter discountsuplimentar;

    private IntegerFilter trusalegislativa;

    private IntegerFilter pretfinal;

    private IntegerFilter avansplatit;

    private IntegerFilter restdeplata;

    private IntegerFilter customertrxid;

    private StringFilter rezcustid;

    private IntegerFilter soldcustid;

    private BooleanFilter proforma;

    private BooleanFilter transport;

    private LongFilter dealerId;

    public PortofoliuCriteria(){
    }

    public PortofoliuCriteria(PortofoliuCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.htrocarno = other.htrocarno == null ? null : other.htrocarno.copy();
        this.dealer = other.dealer == null ? null : other.dealer.copy();
        this.datarezsaufactura = other.datarezsaufactura == null ? null : other.datarezsaufactura.copy();
        this.dataexpirare = other.dataexpirare == null ? null : other.dataexpirare.copy();
        this.resdealerid = other.resdealerid == null ? null : other.resdealerid.copy();
        this.tiplinie = other.tiplinie == null ? null : other.tiplinie.copy();
        this.locatie = other.locatie == null ? null : other.locatie.copy();
        this.lunaproductie = other.lunaproductie == null ? null : other.lunaproductie.copy();
        this.lunasosireintara = other.lunasosireintara == null ? null : other.lunasosireintara.copy();
        this.codmodel = other.codmodel == null ? null : other.codmodel.copy();
        this.tipautovehicul = other.tipautovehicul == null ? null : other.tipautovehicul.copy();
        this.codculoareext = other.codculoareext == null ? null : other.codculoareext.copy();
        this.culoareexterior = other.culoareexterior == null ? null : other.culoareexterior.copy();
        this.culoareIntegererior = other.culoareIntegererior == null ? null : other.culoareIntegererior.copy();
        this.observatii = other.observatii == null ? null : other.observatii.copy();
        this.numeclient = other.numeclient == null ? null : other.numeclient.copy();
        this.numevanzator = other.numevanzator == null ? null : other.numevanzator.copy();
        this.vin = other.vin == null ? null : other.vin.copy();
        this.engineno = other.engineno == null ? null : other.engineno.copy();
        this.anfabricatiecfciv = other.anfabricatiecfciv == null ? null : other.anfabricatiecfciv.copy();
        this.omologareindividuala = other.omologareindividuala == null ? null : other.omologareindividuala.copy();
        this.pretlista = other.pretlista == null ? null : other.pretlista.copy();
        this.discountstandard = other.discountstandard == null ? null : other.discountstandard.copy();
        this.discountsuplimentar = other.discountsuplimentar == null ? null : other.discountsuplimentar.copy();
        this.trusalegislativa = other.trusalegislativa == null ? null : other.trusalegislativa.copy();
        this.pretfinal = other.pretfinal == null ? null : other.pretfinal.copy();
        this.avansplatit = other.avansplatit == null ? null : other.avansplatit.copy();
        this.restdeplata = other.restdeplata == null ? null : other.restdeplata.copy();
        this.customertrxid = other.customertrxid == null ? null : other.customertrxid.copy();
        this.rezcustid = other.rezcustid == null ? null : other.rezcustid.copy();
        this.soldcustid = other.soldcustid == null ? null : other.soldcustid.copy();
        this.proforma = other.proforma == null ? null : other.proforma.copy();
        this.transport = other.transport == null ? null : other.transport.copy();
        this.dealerId = other.dealerId == null ? null : other.dealerId.copy();
    }

    @Override
    public PortofoliuCriteria copy() {
        return new PortofoliuCriteria(this);
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

    public StringFilter getDealer() {
        return dealer;
    }

    public void setDealer(StringFilter dealer) {
        this.dealer = dealer;
    }

    public StringFilter getDatarezsaufactura() {
        return datarezsaufactura;
    }

    public void setDatarezsaufactura(StringFilter datarezsaufactura) {
        this.datarezsaufactura = datarezsaufactura;
    }

    public StringFilter getDataexpirare() {
        return dataexpirare;
    }

    public void setDataexpirare(StringFilter dataexpirare) {
        this.dataexpirare = dataexpirare;
    }

    public IntegerFilter getResdealerid() {
        return resdealerid;
    }

    public void setResdealerid(IntegerFilter resdealerid) {
        this.resdealerid = resdealerid;
    }

    public StringFilter getTiplinie() {
        return tiplinie;
    }

    public void setTiplinie(StringFilter tiplinie) {
        this.tiplinie = tiplinie;
    }

    public StringFilter getLocatie() {
        return locatie;
    }

    public void setLocatie(StringFilter locatie) {
        this.locatie = locatie;
    }

    public StringFilter getLunaproductie() {
        return lunaproductie;
    }

    public void setLunaproductie(StringFilter lunaproductie) {
        this.lunaproductie = lunaproductie;
    }

    public StringFilter getLunasosireintara() {
        return lunasosireintara;
    }

    public void setLunasosireintara(StringFilter lunasosireintara) {
        this.lunasosireintara = lunasosireintara;
    }

    public StringFilter getCodmodel() {
        return codmodel;
    }

    public void setCodmodel(StringFilter codmodel) {
        this.codmodel = codmodel;
    }

    public StringFilter getTipautovehicul() {
        return tipautovehicul;
    }

    public void setTipautovehicul(StringFilter tipautovehicul) {
        this.tipautovehicul = tipautovehicul;
    }

    public StringFilter getCodculoareext() {
        return codculoareext;
    }

    public void setCodculoareext(StringFilter codculoareext) {
        this.codculoareext = codculoareext;
    }

    public StringFilter getCuloareexterior() {
        return culoareexterior;
    }

    public void setCuloareexterior(StringFilter culoareexterior) {
        this.culoareexterior = culoareexterior;
    }

    public StringFilter getCuloareIntegererior() {
        return culoareIntegererior;
    }

    public void setCuloareIntegererior(StringFilter culoareIntegererior) {
        this.culoareIntegererior = culoareIntegererior;
    }

    public StringFilter getObservatii() {
        return observatii;
    }

    public void setObservatii(StringFilter observatii) {
        this.observatii = observatii;
    }

    public StringFilter getNumeclient() {
        return numeclient;
    }

    public void setNumeclient(StringFilter numeclient) {
        this.numeclient = numeclient;
    }

    public StringFilter getNumevanzator() {
        return numevanzator;
    }

    public void setNumevanzator(StringFilter numevanzator) {
        this.numevanzator = numevanzator;
    }

    public StringFilter getVin() {
        return vin;
    }

    public void setVin(StringFilter vin) {
        this.vin = vin;
    }

    public StringFilter getEngineno() {
        return engineno;
    }

    public void setEngineno(StringFilter engineno) {
        this.engineno = engineno;
    }

    public IntegerFilter getAnfabricatiecfciv() {
        return anfabricatiecfciv;
    }

    public void setAnfabricatiecfciv(IntegerFilter anfabricatiecfciv) {
        this.anfabricatiecfciv = anfabricatiecfciv;
    }

    public StringFilter getOmologareindividuala() {
        return omologareindividuala;
    }

    public void setOmologareindividuala(StringFilter omologareindividuala) {
        this.omologareindividuala = omologareindividuala;
    }

    public IntegerFilter getPretlista() {
        return pretlista;
    }

    public void setPretlista(IntegerFilter pretlista) {
        this.pretlista = pretlista;
    }

    public IntegerFilter getDiscountstandard() {
        return discountstandard;
    }

    public void setDiscountstandard(IntegerFilter discountstandard) {
        this.discountstandard = discountstandard;
    }

    public IntegerFilter getDiscountsuplimentar() {
        return discountsuplimentar;
    }

    public void setDiscountsuplimentar(IntegerFilter discountsuplimentar) {
        this.discountsuplimentar = discountsuplimentar;
    }

    public IntegerFilter getTrusalegislativa() {
        return trusalegislativa;
    }

    public void setTrusalegislativa(IntegerFilter trusalegislativa) {
        this.trusalegislativa = trusalegislativa;
    }

    public IntegerFilter getPretfinal() {
        return pretfinal;
    }

    public void setPretfinal(IntegerFilter pretfinal) {
        this.pretfinal = pretfinal;
    }

    public IntegerFilter getAvansplatit() {
        return avansplatit;
    }

    public void setAvansplatit(IntegerFilter avansplatit) {
        this.avansplatit = avansplatit;
    }

    public IntegerFilter getRestdeplata() {
        return restdeplata;
    }

    public void setRestdeplata(IntegerFilter restdeplata) {
        this.restdeplata = restdeplata;
    }

    public IntegerFilter getCustomertrxid() {
        return customertrxid;
    }

    public void setCustomertrxid(IntegerFilter customertrxid) {
        this.customertrxid = customertrxid;
    }

    public StringFilter getRezcustid() {
        return rezcustid;
    }

    public void setRezcustid(StringFilter rezcustid) {
        this.rezcustid = rezcustid;
    }

    public IntegerFilter getSoldcustid() {
        return soldcustid;
    }

    public void setSoldcustid(IntegerFilter soldcustid) {
        this.soldcustid = soldcustid;
    }

    public BooleanFilter getProforma() {
        return proforma;
    }

    public void setProforma(BooleanFilter proforma) {
        this.proforma = proforma;
    }

    public BooleanFilter getTransport() {
        return transport;
    }

    public void setTransport(BooleanFilter transport) {
        this.transport = transport;
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
        final PortofoliuCriteria that = (PortofoliuCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(htrocarno, that.htrocarno) &&
            Objects.equals(dealer, that.dealer) &&
            Objects.equals(datarezsaufactura, that.datarezsaufactura) &&
            Objects.equals(dataexpirare, that.dataexpirare) &&
            Objects.equals(resdealerid, that.resdealerid) &&
            Objects.equals(tiplinie, that.tiplinie) &&
            Objects.equals(locatie, that.locatie) &&
            Objects.equals(lunaproductie, that.lunaproductie) &&
            Objects.equals(lunasosireintara, that.lunasosireintara) &&
            Objects.equals(codmodel, that.codmodel) &&
            Objects.equals(tipautovehicul, that.tipautovehicul) &&
            Objects.equals(codculoareext, that.codculoareext) &&
            Objects.equals(culoareexterior, that.culoareexterior) &&
            Objects.equals(culoareIntegererior, that.culoareIntegererior) &&
            Objects.equals(observatii, that.observatii) &&
            Objects.equals(numeclient, that.numeclient) &&
            Objects.equals(numevanzator, that.numevanzator) &&
            Objects.equals(vin, that.vin) &&
            Objects.equals(engineno, that.engineno) &&
            Objects.equals(anfabricatiecfciv, that.anfabricatiecfciv) &&
            Objects.equals(omologareindividuala, that.omologareindividuala) &&
            Objects.equals(pretlista, that.pretlista) &&
            Objects.equals(discountstandard, that.discountstandard) &&
            Objects.equals(discountsuplimentar, that.discountsuplimentar) &&
            Objects.equals(trusalegislativa, that.trusalegislativa) &&
            Objects.equals(pretfinal, that.pretfinal) &&
            Objects.equals(avansplatit, that.avansplatit) &&
            Objects.equals(restdeplata, that.restdeplata) &&
            Objects.equals(customertrxid, that.customertrxid) &&
            Objects.equals(rezcustid, that.rezcustid) &&
            Objects.equals(soldcustid, that.soldcustid) &&
            Objects.equals(proforma, that.proforma) &&
            Objects.equals(transport, that.transport) &&
            Objects.equals(dealerId, that.dealerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        htrocarno,
        dealer,
        datarezsaufactura,
        dataexpirare,
        resdealerid,
        tiplinie,
        locatie,
        lunaproductie,
        lunasosireintara,
        codmodel,
        tipautovehicul,
        codculoareext,
        culoareexterior,
        culoareIntegererior,
        observatii,
        numeclient,
        numevanzator,
        vin,
        engineno,
        anfabricatiecfciv,
        omologareindividuala,
        pretlista,
        discountstandard,
        discountsuplimentar,
        trusalegislativa,
        pretfinal,
        avansplatit,
        restdeplata,
        customertrxid,
        rezcustid,
        soldcustid,
        proforma,
        transport,
        dealerId
        );
    }

    @Override
    public String toString() {
        return "PortofoliuCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (htrocarno != null ? "htrocarno=" + htrocarno + ", " : "") +
                (dealer != null ? "dealer=" + dealer + ", " : "") +
                (datarezsaufactura != null ? "datarezsaufactura=" + datarezsaufactura + ", " : "") +
                (dataexpirare != null ? "dataexpirare=" + dataexpirare + ", " : "") +
                (resdealerid != null ? "resdealerid=" + resdealerid + ", " : "") +
                (tiplinie != null ? "tiplinie=" + tiplinie + ", " : "") +
                (locatie != null ? "locatie=" + locatie + ", " : "") +
                (lunaproductie != null ? "lunaproductie=" + lunaproductie + ", " : "") +
                (lunasosireintara != null ? "lunasosireintara=" + lunasosireintara + ", " : "") +
                (codmodel != null ? "codmodel=" + codmodel + ", " : "") +
                (tipautovehicul != null ? "tipautovehicul=" + tipautovehicul + ", " : "") +
                (codculoareext != null ? "codculoareext=" + codculoareext + ", " : "") +
                (culoareexterior != null ? "culoareexterior=" + culoareexterior + ", " : "") +
                (culoareIntegererior != null ? "culoareIntegererior=" + culoareIntegererior + ", " : "") +
                (observatii != null ? "observatii=" + observatii + ", " : "") +
                (numeclient != null ? "numeclient=" + numeclient + ", " : "") +
                (numevanzator != null ? "numevanzator=" + numevanzator + ", " : "") +
                (vin != null ? "vin=" + vin + ", " : "") +
                (engineno != null ? "engineno=" + engineno + ", " : "") +
                (anfabricatiecfciv != null ? "anfabricatiecfciv=" + anfabricatiecfciv + ", " : "") +
                (omologareindividuala != null ? "omologareindividuala=" + omologareindividuala + ", " : "") +
                (pretlista != null ? "pretlista=" + pretlista + ", " : "") +
                (discountstandard != null ? "discountstandard=" + discountstandard + ", " : "") +
                (discountsuplimentar != null ? "discountsuplimentar=" + discountsuplimentar + ", " : "") +
                (trusalegislativa != null ? "trusalegislativa=" + trusalegislativa + ", " : "") +
                (pretfinal != null ? "pretfinal=" + pretfinal + ", " : "") +
                (avansplatit != null ? "avansplatit=" + avansplatit + ", " : "") +
                (restdeplata != null ? "restdeplata=" + restdeplata + ", " : "") +
                (customertrxid != null ? "customertrxid=" + customertrxid + ", " : "") +
                (rezcustid != null ? "rezcustid=" + rezcustid + ", " : "") +
                (soldcustid != null ? "soldcustid=" + soldcustid + ", " : "") +
                (proforma != null ? "proforma=" + proforma + ", " : "") +
                (transport != null ? "transport=" + transport + ", " : "") +
                (dealerId != null ? "dealerId=" + dealerId + ", " : "") +
            "}";
    }

}

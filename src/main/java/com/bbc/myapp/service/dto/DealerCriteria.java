package com.bbc.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.bbc.myapp.domain.enumeration.TipuriAuto;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bbc.myapp.domain.Dealer} entity. This class is used
 * in {@link com.bbc.myapp.web.rest.DealerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dealers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DealerCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TipuriAuto
     */
    public static class TipuriAutoFilter extends Filter<TipuriAuto> {

        public TipuriAutoFilter() {
        }

        public TipuriAutoFilter(TipuriAutoFilter filter) {
            super(filter);
        }

        @Override
        public TipuriAutoFilter copy() {
            return new TipuriAutoFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private TipuriAutoFilter tipAutovehicule;

    private StringFilter dealerId;

    private LongFilter userId;

    private LongFilter stocId;

    private LongFilter portofoliuId;

    public DealerCriteria(){
    }

    public DealerCriteria(DealerCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.tipAutovehicule = other.tipAutovehicule == null ? null : other.tipAutovehicule.copy();
        this.dealerId = other.dealerId == null ? null : other.dealerId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.stocId = other.stocId == null ? null : other.stocId.copy();
        this.portofoliuId = other.portofoliuId == null ? null : other.portofoliuId.copy();
    }

    @Override
    public DealerCriteria copy() {
        return new DealerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public TipuriAutoFilter getTipAutovehicule() {
        return tipAutovehicule;
    }

    public void setTipAutovehicule(TipuriAutoFilter tipAutovehicule) {
        this.tipAutovehicule = tipAutovehicule;
    }

    public StringFilter getDealerId() {
        return dealerId;
    }

    public void setDealerId(StringFilter dealerId) {
        this.dealerId = dealerId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getStocId() {
        return stocId;
    }

    public void setStocId(LongFilter stocId) {
        this.stocId = stocId;
    }

    public LongFilter getPortofoliuId() {
        return portofoliuId;
    }

    public void setPortofoliuId(LongFilter portofoliuId) {
        this.portofoliuId = portofoliuId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DealerCriteria that = (DealerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(tipAutovehicule, that.tipAutovehicule) &&
            Objects.equals(dealerId, that.dealerId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(stocId, that.stocId) &&
            Objects.equals(portofoliuId, that.portofoliuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        description,
        tipAutovehicule,
        dealerId,
        userId,
        stocId,
        portofoliuId
        );
    }

    @Override
    public String toString() {
        return "DealerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (tipAutovehicule != null ? "tipAutovehicule=" + tipAutovehicule + ", " : "") +
                (dealerId != null ? "dealerId=" + dealerId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (stocId != null ? "stocId=" + stocId + ", " : "") +
                (portofoliuId != null ? "portofoliuId=" + portofoliuId + ", " : "") +
            "}";
    }

}

package com.bbc.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.bbc.myapp.domain.enumeration.TipuriAuto;

/**
 * A DTO for the {@link com.bbc.myapp.domain.Dealer} entity.
 */
public class DealerDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private TipuriAuto tipAutovehicule;

    @NotNull
    private String dealerId;


    private Set<UserDTO> users = new HashSet<>();

    private Long stocId;

    private String stocResdealerid;

    private Long portofoliuId;

    private String portofoliuResdealerid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TipuriAuto getTipAutovehicule() {
        return tipAutovehicule;
    }

    public void setTipAutovehicule(TipuriAuto tipAutovehicule) {
        this.tipAutovehicule = tipAutovehicule;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public Long getStocId() {
        return stocId;
    }

    public void setStocId(Long stocId) {
        this.stocId = stocId;
    }

    public String getStocResdealerid() {
        return stocResdealerid;
    }

    public void setStocResdealerid(String stocResdealerid) {
        this.stocResdealerid = stocResdealerid;
    }

    public Long getPortofoliuId() {
        return portofoliuId;
    }

    public void setPortofoliuId(Long portofoliuId) {
        this.portofoliuId = portofoliuId;
    }

    public String getPortofoliuResdealerid() {
        return portofoliuResdealerid;
    }

    public void setPortofoliuResdealerid(String portofoliuResdealerid) {
        this.portofoliuResdealerid = portofoliuResdealerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DealerDTO dealerDTO = (DealerDTO) o;
        if (dealerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dealerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DealerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tipAutovehicule='" + getTipAutovehicule() + "'" +
            ", dealerId='" + getDealerId() + "'" +
            ", stoc=" + getStocId() +
            ", stoc='" + getStocResdealerid() + "'" +
            ", portofoliu=" + getPortofoliuId() +
            ", portofoliu='" + getPortofoliuResdealerid() + "'" +
            "}";
    }
}

package com.bbc.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.bbc.myapp.domain.enumeration.TipuriAuto;

/**
 * A Dealer.
 */
@Entity
@Table(name = "dealer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dealer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip_autovehicule")
    private TipuriAuto tipAutovehicule;

    @NotNull
    @Column(name = "dealer_id", nullable = false)
    private String dealerId;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "dealer_user",
               joinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("dealers")
    private Stoc stoc;

    @ManyToOne
    @JsonIgnoreProperties("dealers")
    private Portofoliu portofoliu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Dealer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Dealer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TipuriAuto getTipAutovehicule() {
        return tipAutovehicule;
    }

    public Dealer tipAutovehicule(TipuriAuto tipAutovehicule) {
        this.tipAutovehicule = tipAutovehicule;
        return this;
    }

    public void setTipAutovehicule(TipuriAuto tipAutovehicule) {
        this.tipAutovehicule = tipAutovehicule;
    }

    public String getDealerId() {
        return dealerId;
    }

    public Dealer dealerId(String dealerId) {
        this.dealerId = dealerId;
        return this;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Dealer users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Dealer addUser(User user) {
        this.users.add(user);
        return this;
    }

    public Dealer removeUser(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Stoc getStoc() {
        return stoc;
    }

    public Dealer stoc(Stoc stoc) {
        this.stoc = stoc;
        return this;
    }

    public void setStoc(Stoc stoc) {
        this.stoc = stoc;
    }

    public Portofoliu getPortofoliu() {
        return portofoliu;
    }

    public Dealer portofoliu(Portofoliu portofoliu) {
        this.portofoliu = portofoliu;
        return this;
    }

    public void setPortofoliu(Portofoliu portofoliu) {
        this.portofoliu = portofoliu;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dealer)) {
            return false;
        }
        return id != null && id.equals(((Dealer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dealer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tipAutovehicule='" + getTipAutovehicule() + "'" +
            ", dealerId='" + getDealerId() + "'" +
            "}";
    }
}

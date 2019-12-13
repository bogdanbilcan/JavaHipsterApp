package com.bbc.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.bbc.myapp.domain.Portofoliu;
import com.bbc.myapp.domain.*; // for static metamodels
import com.bbc.myapp.repository.PortofoliuRepository;
import com.bbc.myapp.service.dto.PortofoliuCriteria;
import com.bbc.myapp.service.dto.PortofoliuDTO;
import com.bbc.myapp.service.mapper.PortofoliuMapper;

/**
 * Service for executing complex queries for {@link Portofoliu} entities in the database.
 * The main input is a {@link PortofoliuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PortofoliuDTO} or a {@link Page} of {@link PortofoliuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PortofoliuQueryService extends QueryService<Portofoliu> {

    private final Logger log = LoggerFactory.getLogger(PortofoliuQueryService.class);

    private final PortofoliuRepository portofoliuRepository;

    private final PortofoliuMapper portofoliuMapper;

    public PortofoliuQueryService(PortofoliuRepository portofoliuRepository, PortofoliuMapper portofoliuMapper) {
        this.portofoliuRepository = portofoliuRepository;
        this.portofoliuMapper = portofoliuMapper;
    }

    /**
     * Return a {@link List} of {@link PortofoliuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PortofoliuDTO> findByCriteria(PortofoliuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Portofoliu> specification = createSpecification(criteria);
        return portofoliuMapper.toDto(portofoliuRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PortofoliuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PortofoliuDTO> findByCriteria(PortofoliuCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Portofoliu> specification = createSpecification(criteria);
        return portofoliuRepository.findAll(specification, page)
            .map(portofoliuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PortofoliuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Portofoliu> specification = createSpecification(criteria);
        return portofoliuRepository.count(specification);
    }

    /**
     * Function to convert {@link PortofoliuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Portofoliu> createSpecification(PortofoliuCriteria criteria) {
        Specification<Portofoliu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Portofoliu_.id));
            }
            if (criteria.getHtrocarno() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHtrocarno(), Portofoliu_.htrocarno));
            }
            if (criteria.getDealer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDealer(), Portofoliu_.dealer));
            }
            if (criteria.getDatarezsaufactura() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDatarezsaufactura(), Portofoliu_.datarezsaufactura));
            }
            if (criteria.getDataexpirare() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDataexpirare(), Portofoliu_.dataexpirare));
            }
            if (criteria.getResdealerid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResdealerid(), Portofoliu_.resdealerid));
            }
            if (criteria.getTiplinie() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTiplinie(), Portofoliu_.tiplinie));
            }
            if (criteria.getLocatie() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocatie(), Portofoliu_.locatie));
            }
            if (criteria.getLunaproductie() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLunaproductie(), Portofoliu_.lunaproductie));
            }
            if (criteria.getLunasosireintara() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLunasosireintara(), Portofoliu_.lunasosireintara));
            }
            if (criteria.getCodmodel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodmodel(), Portofoliu_.codmodel));
            }
            if (criteria.getTipautovehicul() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipautovehicul(), Portofoliu_.tipautovehicul));
            }
            if (criteria.getCodculoareext() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodculoareext(), Portofoliu_.codculoareext));
            }
            if (criteria.getCuloareexterior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCuloareexterior(), Portofoliu_.culoareexterior));
            }
            if (criteria.getCuloareIntegererior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCuloareIntegererior(), Portofoliu_.culoareIntegererior));
            }
            if (criteria.getObservatii() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservatii(), Portofoliu_.observatii));
            }
            if (criteria.getNumeclient() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeclient(), Portofoliu_.numeclient));
            }
            if (criteria.getNumevanzator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumevanzator(), Portofoliu_.numevanzator));
            }
            if (criteria.getVin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVin(), Portofoliu_.vin));
            }
            if (criteria.getEngineno() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEngineno(), Portofoliu_.engineno));
            }
            if (criteria.getAnfabricatiecfciv() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnfabricatiecfciv(), Portofoliu_.anfabricatiecfciv));
            }
            if (criteria.getOmologareindividuala() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOmologareindividuala(), Portofoliu_.omologareindividuala));
            }
            if (criteria.getPretlista() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPretlista(), Portofoliu_.pretlista));
            }
            if (criteria.getDiscountstandard() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscountstandard(), Portofoliu_.discountstandard));
            }
            if (criteria.getDiscountsuplimentar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscountsuplimentar(), Portofoliu_.discountsuplimentar));
            }
            if (criteria.getTrusalegislativa() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrusalegislativa(), Portofoliu_.trusalegislativa));
            }
            if (criteria.getPretfinal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPretfinal(), Portofoliu_.pretfinal));
            }
            if (criteria.getAvansplatit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvansplatit(), Portofoliu_.avansplatit));
            }
            if (criteria.getRestdeplata() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRestdeplata(), Portofoliu_.restdeplata));
            }
            if (criteria.getCustomertrxid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCustomertrxid(), Portofoliu_.customertrxid));
            }
            if (criteria.getRezcustid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRezcustid(), Portofoliu_.rezcustid));
            }
            if (criteria.getSoldcustid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoldcustid(), Portofoliu_.soldcustid));
            }
            if (criteria.getProforma() != null) {
                specification = specification.and(buildSpecification(criteria.getProforma(), Portofoliu_.proforma));
            }
            if (criteria.getTransport() != null) {
                specification = specification.and(buildSpecification(criteria.getTransport(), Portofoliu_.transport));
            }
            if (criteria.getDealerId() != null) {
                specification = specification.and(buildSpecification(criteria.getDealerId(),
                    root -> root.join(Portofoliu_.dealers, JoinType.LEFT).get(Dealer_.id)));
            }
        }
        return specification;
    }
}

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

import com.bbc.myapp.domain.Stoc;
import com.bbc.myapp.domain.*; // for static metamodels
import com.bbc.myapp.repository.StocRepository;
import com.bbc.myapp.service.dto.StocCriteria;
import com.bbc.myapp.service.dto.StocDTO;
import com.bbc.myapp.service.mapper.StocMapper;

/**
 * Service for executing complex queries for {@link Stoc} entities in the database.
 * The main input is a {@link StocCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link StocDTO} or a {@link Page} of {@link StocDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StocQueryService extends QueryService<Stoc> {

    private final Logger log = LoggerFactory.getLogger(StocQueryService.class);

    private final StocRepository stocRepository;

    private final StocMapper stocMapper;

    public StocQueryService(StocRepository stocRepository, StocMapper stocMapper) {
        this.stocRepository = stocRepository;
        this.stocMapper = stocMapper;
    }

    /**
     * Return a {@link List} of {@link StocDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StocDTO> findByCriteria(StocCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Stoc> specification = createSpecification(criteria);
        return stocMapper.toDto(stocRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link StocDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StocDTO> findByCriteria(StocCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Stoc> specification = createSpecification(criteria);
        return stocRepository.findAll(specification, page)
            .map(stocMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StocCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Stoc> specification = createSpecification(criteria);
        return stocRepository.count(specification);
    }

    /**
     * Function to convert {@link StocCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Stoc> createSpecification(StocCriteria criteria) {
        Specification<Stoc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Stoc_.id));
            }
            if (criteria.getHtrocarno() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHtrocarno(), Stoc_.htrocarno));
            }
            if (criteria.getResdealerid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResdealerid(), Stoc_.resdealerid));
            }
            if (criteria.getAnfabricatieciv() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnfabricatieciv(), Stoc_.anfabricatieciv));
            }
            if (criteria.getTipautovehicul() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipautovehicul(), Stoc_.tipautovehicul));
            }
            if (criteria.getCodculoareexterior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodculoareexterior(), Stoc_.codculoareexterior));
            }
            if (criteria.getDescculoareexterior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescculoareexterior(), Stoc_.descculoareexterior));
            }
            if (criteria.getVopseametalizata() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVopseametalizata(), Stoc_.vopseametalizata));
            }
            if (criteria.getCuloareinterior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCuloareinterior(), Stoc_.culoareinterior));
            }
            if (criteria.getObservatii() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservatii(), Stoc_.observatii));
            }
            if (criteria.getLocatie() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocatie(), Stoc_.locatie));
            }
            if (criteria.getOmologareind() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOmologareind(), Stoc_.omologareind));
            }
            if (criteria.getLunasosireintara() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLunasosireintara(), Stoc_.lunasosireintara));
            }
            if (criteria.getRezervata() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRezervata(), Stoc_.rezervata));
            }
            if (criteria.getDataexpirarerez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDataexpirarerez(), Stoc_.dataexpirarerez));
            }
            if (criteria.getDealerId() != null) {
                specification = specification.and(buildSpecification(criteria.getDealerId(),
                    root -> root.join(Stoc_.dealers, JoinType.LEFT).get(Dealer_.id)));
            }
        }
        return specification;
    }
}

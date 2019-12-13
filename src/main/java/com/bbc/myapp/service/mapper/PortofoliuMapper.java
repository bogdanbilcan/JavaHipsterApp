package com.bbc.myapp.service.mapper;

import com.bbc.myapp.domain.*;
import com.bbc.myapp.service.dto.PortofoliuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Portofoliu} and its DTO {@link PortofoliuDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PortofoliuMapper extends EntityMapper<PortofoliuDTO, Portofoliu> {


    @Mapping(target = "dealers", ignore = true)
    @Mapping(target = "removeDealer", ignore = true)
    Portofoliu toEntity(PortofoliuDTO portofoliuDTO);

    default Portofoliu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Portofoliu portofoliu = new Portofoliu();
        portofoliu.setId(id);
        return portofoliu;
    }
}

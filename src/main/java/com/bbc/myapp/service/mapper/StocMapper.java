package com.bbc.myapp.service.mapper;

import com.bbc.myapp.domain.*;
import com.bbc.myapp.service.dto.StocDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Stoc} and its DTO {@link StocDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StocMapper extends EntityMapper<StocDTO, Stoc> {


    @Mapping(target = "dealers", ignore = true)
    @Mapping(target = "removeDealer", ignore = true)
    Stoc toEntity(StocDTO stocDTO);

    default Stoc fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stoc stoc = new Stoc();
        stoc.setId(id);
        return stoc;
    }
}

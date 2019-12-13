package com.bbc.myapp.service.mapper;

import com.bbc.myapp.domain.*;
import com.bbc.myapp.service.dto.DealerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dealer} and its DTO {@link DealerDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, StocMapper.class, PortofoliuMapper.class})
public interface DealerMapper extends EntityMapper<DealerDTO, Dealer> {

    @Mapping(source = "stoc.id", target = "stocId")
    @Mapping(source = "stoc.resdealerid", target = "stocResdealerid")
    @Mapping(source = "portofoliu.id", target = "portofoliuId")
    @Mapping(source = "portofoliu.resdealerid", target = "portofoliuResdealerid")
    DealerDTO toDto(Dealer dealer);

    @Mapping(target = "removeUser", ignore = true)
    @Mapping(source = "stocId", target = "stoc")
    @Mapping(source = "portofoliuId", target = "portofoliu")
    Dealer toEntity(DealerDTO dealerDTO);

    default Dealer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dealer dealer = new Dealer();
        dealer.setId(id);
        return dealer;
    }
}

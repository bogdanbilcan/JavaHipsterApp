package com.bbc.myapp.service.mapper;

import com.bbc.myapp.domain.*;
import com.bbc.myapp.service.dto.NotifTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifTemplate} and its DTO {@link NotifTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotifTemplateMapper extends EntityMapper<NotifTemplateDTO, NotifTemplate> {



    default NotifTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotifTemplate notifTemplate = new NotifTemplate();
        notifTemplate.setId(id);
        return notifTemplate;
    }
}

package com.bbc.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NotifTemplateMapperTest {

    private NotifTemplateMapper notifTemplateMapper;

    @BeforeEach
    public void setUp() {
        notifTemplateMapper = new NotifTemplateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(notifTemplateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notifTemplateMapper.fromId(null)).isNull();
    }
}

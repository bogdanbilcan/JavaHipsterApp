package com.bbc.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class StocMapperTest {

    private StocMapper stocMapper;

    @BeforeEach
    public void setUp() {
        stocMapper = new StocMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(stocMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(stocMapper.fromId(null)).isNull();
    }
}

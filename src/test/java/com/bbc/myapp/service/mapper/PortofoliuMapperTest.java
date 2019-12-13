package com.bbc.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PortofoliuMapperTest {

    private PortofoliuMapper portofoliuMapper;

    @BeforeEach
    public void setUp() {
        portofoliuMapper = new PortofoliuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(portofoliuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(portofoliuMapper.fromId(null)).isNull();
    }
}

package com.bbc.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bbc.myapp.web.rest.TestUtil;

public class PortofoliuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PortofoliuDTO.class);
        PortofoliuDTO portofoliuDTO1 = new PortofoliuDTO();
        portofoliuDTO1.setId(1L);
        PortofoliuDTO portofoliuDTO2 = new PortofoliuDTO();
        assertThat(portofoliuDTO1).isNotEqualTo(portofoliuDTO2);
        portofoliuDTO2.setId(portofoliuDTO1.getId());
        assertThat(portofoliuDTO1).isEqualTo(portofoliuDTO2);
        portofoliuDTO2.setId(2L);
        assertThat(portofoliuDTO1).isNotEqualTo(portofoliuDTO2);
        portofoliuDTO1.setId(null);
        assertThat(portofoliuDTO1).isNotEqualTo(portofoliuDTO2);
    }
}

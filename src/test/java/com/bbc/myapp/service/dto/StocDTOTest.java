package com.bbc.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bbc.myapp.web.rest.TestUtil;

public class StocDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StocDTO.class);
        StocDTO stocDTO1 = new StocDTO();
        stocDTO1.setId(1L);
        StocDTO stocDTO2 = new StocDTO();
        assertThat(stocDTO1).isNotEqualTo(stocDTO2);
        stocDTO2.setId(stocDTO1.getId());
        assertThat(stocDTO1).isEqualTo(stocDTO2);
        stocDTO2.setId(2L);
        assertThat(stocDTO1).isNotEqualTo(stocDTO2);
        stocDTO1.setId(null);
        assertThat(stocDTO1).isNotEqualTo(stocDTO2);
    }
}

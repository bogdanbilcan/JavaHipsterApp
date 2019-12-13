package com.bbc.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bbc.myapp.web.rest.TestUtil;

public class NotifTemplateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifTemplateDTO.class);
        NotifTemplateDTO notifTemplateDTO1 = new NotifTemplateDTO();
        notifTemplateDTO1.setId(1L);
        NotifTemplateDTO notifTemplateDTO2 = new NotifTemplateDTO();
        assertThat(notifTemplateDTO1).isNotEqualTo(notifTemplateDTO2);
        notifTemplateDTO2.setId(notifTemplateDTO1.getId());
        assertThat(notifTemplateDTO1).isEqualTo(notifTemplateDTO2);
        notifTemplateDTO2.setId(2L);
        assertThat(notifTemplateDTO1).isNotEqualTo(notifTemplateDTO2);
        notifTemplateDTO1.setId(null);
        assertThat(notifTemplateDTO1).isNotEqualTo(notifTemplateDTO2);
    }
}

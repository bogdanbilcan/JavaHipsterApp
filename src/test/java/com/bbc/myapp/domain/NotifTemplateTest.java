package com.bbc.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bbc.myapp.web.rest.TestUtil;

public class NotifTemplateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifTemplate.class);
        NotifTemplate notifTemplate1 = new NotifTemplate();
        notifTemplate1.setId(1L);
        NotifTemplate notifTemplate2 = new NotifTemplate();
        notifTemplate2.setId(notifTemplate1.getId());
        assertThat(notifTemplate1).isEqualTo(notifTemplate2);
        notifTemplate2.setId(2L);
        assertThat(notifTemplate1).isNotEqualTo(notifTemplate2);
        notifTemplate1.setId(null);
        assertThat(notifTemplate1).isNotEqualTo(notifTemplate2);
    }
}

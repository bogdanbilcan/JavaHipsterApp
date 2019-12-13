package com.bbc.myapp.web.rest;

import com.bbc.myapp.JavaHipsterApp;
import com.bbc.myapp.domain.NotifTemplate;
import com.bbc.myapp.repository.NotifTemplateRepository;
import com.bbc.myapp.service.NotifTemplateService;
import com.bbc.myapp.service.dto.NotifTemplateDTO;
import com.bbc.myapp.service.mapper.NotifTemplateMapper;
import com.bbc.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bbc.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NotifTemplateResource} REST controller.
 */
@SpringBootTest(classes = JavaHipsterApp.class)
public class NotifTemplateResourceIT {

    private static final String DEFAULT_EMAIL_ADDRESSES = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESSES = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private NotifTemplateRepository notifTemplateRepository;

    @Autowired
    private NotifTemplateMapper notifTemplateMapper;

    @Autowired
    private NotifTemplateService notifTemplateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restNotifTemplateMockMvc;

    private NotifTemplate notifTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotifTemplateResource notifTemplateResource = new NotifTemplateResource(notifTemplateService);
        this.restNotifTemplateMockMvc = MockMvcBuilders.standaloneSetup(notifTemplateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifTemplate createEntity(EntityManager em) {
        NotifTemplate notifTemplate = new NotifTemplate()
            .emailAddresses(DEFAULT_EMAIL_ADDRESSES)
            .message(DEFAULT_MESSAGE);
        return notifTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotifTemplate createUpdatedEntity(EntityManager em) {
        NotifTemplate notifTemplate = new NotifTemplate()
            .emailAddresses(UPDATED_EMAIL_ADDRESSES)
            .message(UPDATED_MESSAGE);
        return notifTemplate;
    }

    @BeforeEach
    public void initTest() {
        notifTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotifTemplate() throws Exception {
        int databaseSizeBeforeCreate = notifTemplateRepository.findAll().size();

        // Create the NotifTemplate
        NotifTemplateDTO notifTemplateDTO = notifTemplateMapper.toDto(notifTemplate);
        restNotifTemplateMockMvc.perform(post("/api/notif-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notifTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the NotifTemplate in the database
        List<NotifTemplate> notifTemplateList = notifTemplateRepository.findAll();
        assertThat(notifTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        NotifTemplate testNotifTemplate = notifTemplateList.get(notifTemplateList.size() - 1);
        assertThat(testNotifTemplate.getEmailAddresses()).isEqualTo(DEFAULT_EMAIL_ADDRESSES);
        assertThat(testNotifTemplate.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createNotifTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notifTemplateRepository.findAll().size();

        // Create the NotifTemplate with an existing ID
        notifTemplate.setId(1L);
        NotifTemplateDTO notifTemplateDTO = notifTemplateMapper.toDto(notifTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotifTemplateMockMvc.perform(post("/api/notif-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notifTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotifTemplate in the database
        List<NotifTemplate> notifTemplateList = notifTemplateRepository.findAll();
        assertThat(notifTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmailAddressesIsRequired() throws Exception {
        int databaseSizeBeforeTest = notifTemplateRepository.findAll().size();
        // set the field null
        notifTemplate.setEmailAddresses(null);

        // Create the NotifTemplate, which fails.
        NotifTemplateDTO notifTemplateDTO = notifTemplateMapper.toDto(notifTemplate);

        restNotifTemplateMockMvc.perform(post("/api/notif-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notifTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<NotifTemplate> notifTemplateList = notifTemplateRepository.findAll();
        assertThat(notifTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotifTemplates() throws Exception {
        // Initialize the database
        notifTemplateRepository.saveAndFlush(notifTemplate);

        // Get all the notifTemplateList
        restNotifTemplateMockMvc.perform(get("/api/notif-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].emailAddresses").value(hasItem(DEFAULT_EMAIL_ADDRESSES)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
    }
    
    @Test
    @Transactional
    public void getNotifTemplate() throws Exception {
        // Initialize the database
        notifTemplateRepository.saveAndFlush(notifTemplate);

        // Get the notifTemplate
        restNotifTemplateMockMvc.perform(get("/api/notif-templates/{id}", notifTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notifTemplate.getId().intValue()))
            .andExpect(jsonPath("$.emailAddresses").value(DEFAULT_EMAIL_ADDRESSES))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE));
    }

    @Test
    @Transactional
    public void getNonExistingNotifTemplate() throws Exception {
        // Get the notifTemplate
        restNotifTemplateMockMvc.perform(get("/api/notif-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotifTemplate() throws Exception {
        // Initialize the database
        notifTemplateRepository.saveAndFlush(notifTemplate);

        int databaseSizeBeforeUpdate = notifTemplateRepository.findAll().size();

        // Update the notifTemplate
        NotifTemplate updatedNotifTemplate = notifTemplateRepository.findById(notifTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedNotifTemplate are not directly saved in db
        em.detach(updatedNotifTemplate);
        updatedNotifTemplate
            .emailAddresses(UPDATED_EMAIL_ADDRESSES)
            .message(UPDATED_MESSAGE);
        NotifTemplateDTO notifTemplateDTO = notifTemplateMapper.toDto(updatedNotifTemplate);

        restNotifTemplateMockMvc.perform(put("/api/notif-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notifTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the NotifTemplate in the database
        List<NotifTemplate> notifTemplateList = notifTemplateRepository.findAll();
        assertThat(notifTemplateList).hasSize(databaseSizeBeforeUpdate);
        NotifTemplate testNotifTemplate = notifTemplateList.get(notifTemplateList.size() - 1);
        assertThat(testNotifTemplate.getEmailAddresses()).isEqualTo(UPDATED_EMAIL_ADDRESSES);
        assertThat(testNotifTemplate.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingNotifTemplate() throws Exception {
        int databaseSizeBeforeUpdate = notifTemplateRepository.findAll().size();

        // Create the NotifTemplate
        NotifTemplateDTO notifTemplateDTO = notifTemplateMapper.toDto(notifTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotifTemplateMockMvc.perform(put("/api/notif-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notifTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotifTemplate in the database
        List<NotifTemplate> notifTemplateList = notifTemplateRepository.findAll();
        assertThat(notifTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotifTemplate() throws Exception {
        // Initialize the database
        notifTemplateRepository.saveAndFlush(notifTemplate);

        int databaseSizeBeforeDelete = notifTemplateRepository.findAll().size();

        // Delete the notifTemplate
        restNotifTemplateMockMvc.perform(delete("/api/notif-templates/{id}", notifTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotifTemplate> notifTemplateList = notifTemplateRepository.findAll();
        assertThat(notifTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

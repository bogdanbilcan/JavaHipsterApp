package com.bbc.myapp.web.rest;

import com.bbc.myapp.JavaHipsterApp;
import com.bbc.myapp.domain.Dealer;
import com.bbc.myapp.domain.User;
import com.bbc.myapp.domain.Stoc;
import com.bbc.myapp.domain.Portofoliu;
import com.bbc.myapp.repository.DealerRepository;
import com.bbc.myapp.service.DealerService;
import com.bbc.myapp.service.dto.DealerDTO;
import com.bbc.myapp.service.mapper.DealerMapper;
import com.bbc.myapp.web.rest.errors.ExceptionTranslator;
import com.bbc.myapp.service.dto.DealerCriteria;
import com.bbc.myapp.service.DealerQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.bbc.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bbc.myapp.domain.enumeration.TipuriAuto;
/**
 * Integration tests for the {@link DealerResource} REST controller.
 */
@SpringBootTest(classes = JavaHipsterApp.class)
public class DealerResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final TipuriAuto DEFAULT_TIP_AUTOVEHICULE = TipuriAuto.AUTOMOBILE;
    private static final TipuriAuto UPDATED_TIP_AUTOVEHICULE = TipuriAuto.MOTOCILCETE;

    private static final String DEFAULT_DEALER_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEALER_ID = "BBBBBBBBBB";

    @Autowired
    private DealerRepository dealerRepository;

    @Mock
    private DealerRepository dealerRepositoryMock;

    @Autowired
    private DealerMapper dealerMapper;

    @Mock
    private DealerService dealerServiceMock;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private DealerQueryService dealerQueryService;

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

    private MockMvc restDealerMockMvc;

    private Dealer dealer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DealerResource dealerResource = new DealerResource(dealerService, dealerQueryService);
        this.restDealerMockMvc = MockMvcBuilders.standaloneSetup(dealerResource)
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
    public static Dealer createEntity(EntityManager em) {
        Dealer dealer = new Dealer()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .tipAutovehicule(DEFAULT_TIP_AUTOVEHICULE)
            .dealerId(DEFAULT_DEALER_ID);
        return dealer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dealer createUpdatedEntity(EntityManager em) {
        Dealer dealer = new Dealer()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tipAutovehicule(UPDATED_TIP_AUTOVEHICULE)
            .dealerId(UPDATED_DEALER_ID);
        return dealer;
    }

    @BeforeEach
    public void initTest() {
        dealer = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealer() throws Exception {
        int databaseSizeBeforeCreate = dealerRepository.findAll().size();

        // Create the Dealer
        DealerDTO dealerDTO = dealerMapper.toDto(dealer);
        restDealerMockMvc.perform(post("/api/dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerDTO)))
            .andExpect(status().isCreated());

        // Validate the Dealer in the database
        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeCreate + 1);
        Dealer testDealer = dealerList.get(dealerList.size() - 1);
        assertThat(testDealer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDealer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDealer.getTipAutovehicule()).isEqualTo(DEFAULT_TIP_AUTOVEHICULE);
        assertThat(testDealer.getDealerId()).isEqualTo(DEFAULT_DEALER_ID);
    }

    @Test
    @Transactional
    public void createDealerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealerRepository.findAll().size();

        // Create the Dealer with an existing ID
        dealer.setId(1L);
        DealerDTO dealerDTO = dealerMapper.toDto(dealer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealerMockMvc.perform(post("/api/dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dealer in the database
        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealerRepository.findAll().size();
        // set the field null
        dealer.setName(null);

        // Create the Dealer, which fails.
        DealerDTO dealerDTO = dealerMapper.toDto(dealer);

        restDealerMockMvc.perform(post("/api/dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerDTO)))
            .andExpect(status().isBadRequest());

        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDealerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealerRepository.findAll().size();
        // set the field null
        dealer.setDealerId(null);

        // Create the Dealer, which fails.
        DealerDTO dealerDTO = dealerMapper.toDto(dealer);

        restDealerMockMvc.perform(post("/api/dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerDTO)))
            .andExpect(status().isBadRequest());

        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealers() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList
        restDealerMockMvc.perform(get("/api/dealers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tipAutovehicule").value(hasItem(DEFAULT_TIP_AUTOVEHICULE.toString())))
            .andExpect(jsonPath("$.[*].dealerId").value(hasItem(DEFAULT_DEALER_ID)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDealersWithEagerRelationshipsIsEnabled() throws Exception {
        DealerResource dealerResource = new DealerResource(dealerServiceMock, dealerQueryService);
        when(dealerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restDealerMockMvc = MockMvcBuilders.standaloneSetup(dealerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDealerMockMvc.perform(get("/api/dealers?eagerload=true"))
        .andExpect(status().isOk());

        verify(dealerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDealersWithEagerRelationshipsIsNotEnabled() throws Exception {
        DealerResource dealerResource = new DealerResource(dealerServiceMock, dealerQueryService);
            when(dealerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restDealerMockMvc = MockMvcBuilders.standaloneSetup(dealerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDealerMockMvc.perform(get("/api/dealers?eagerload=true"))
        .andExpect(status().isOk());

            verify(dealerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDealer() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get the dealer
        restDealerMockMvc.perform(get("/api/dealers/{id}", dealer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dealer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tipAutovehicule").value(DEFAULT_TIP_AUTOVEHICULE.toString()))
            .andExpect(jsonPath("$.dealerId").value(DEFAULT_DEALER_ID));
    }


    @Test
    @Transactional
    public void getDealersByIdFiltering() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        Long id = dealer.getId();

        defaultDealerShouldBeFound("id.equals=" + id);
        defaultDealerShouldNotBeFound("id.notEquals=" + id);

        defaultDealerShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDealerShouldNotBeFound("id.greaterThan=" + id);

        defaultDealerShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDealerShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDealersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where name equals to DEFAULT_NAME
        defaultDealerShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the dealerList where name equals to UPDATED_NAME
        defaultDealerShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDealersByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where name not equals to DEFAULT_NAME
        defaultDealerShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the dealerList where name not equals to UPDATED_NAME
        defaultDealerShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDealersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDealerShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the dealerList where name equals to UPDATED_NAME
        defaultDealerShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDealersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where name is not null
        defaultDealerShouldBeFound("name.specified=true");

        // Get all the dealerList where name is null
        defaultDealerShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllDealersByNameContainsSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where name contains DEFAULT_NAME
        defaultDealerShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the dealerList where name contains UPDATED_NAME
        defaultDealerShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDealersByNameNotContainsSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where name does not contain DEFAULT_NAME
        defaultDealerShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the dealerList where name does not contain UPDATED_NAME
        defaultDealerShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllDealersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where description equals to DEFAULT_DESCRIPTION
        defaultDealerShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the dealerList where description equals to UPDATED_DESCRIPTION
        defaultDealerShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDealersByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where description not equals to DEFAULT_DESCRIPTION
        defaultDealerShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the dealerList where description not equals to UPDATED_DESCRIPTION
        defaultDealerShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDealersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultDealerShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the dealerList where description equals to UPDATED_DESCRIPTION
        defaultDealerShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDealersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where description is not null
        defaultDealerShouldBeFound("description.specified=true");

        // Get all the dealerList where description is null
        defaultDealerShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllDealersByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where description contains DEFAULT_DESCRIPTION
        defaultDealerShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the dealerList where description contains UPDATED_DESCRIPTION
        defaultDealerShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDealersByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where description does not contain DEFAULT_DESCRIPTION
        defaultDealerShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the dealerList where description does not contain UPDATED_DESCRIPTION
        defaultDealerShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDealersByTipAutovehiculeIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where tipAutovehicule equals to DEFAULT_TIP_AUTOVEHICULE
        defaultDealerShouldBeFound("tipAutovehicule.equals=" + DEFAULT_TIP_AUTOVEHICULE);

        // Get all the dealerList where tipAutovehicule equals to UPDATED_TIP_AUTOVEHICULE
        defaultDealerShouldNotBeFound("tipAutovehicule.equals=" + UPDATED_TIP_AUTOVEHICULE);
    }

    @Test
    @Transactional
    public void getAllDealersByTipAutovehiculeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where tipAutovehicule not equals to DEFAULT_TIP_AUTOVEHICULE
        defaultDealerShouldNotBeFound("tipAutovehicule.notEquals=" + DEFAULT_TIP_AUTOVEHICULE);

        // Get all the dealerList where tipAutovehicule not equals to UPDATED_TIP_AUTOVEHICULE
        defaultDealerShouldBeFound("tipAutovehicule.notEquals=" + UPDATED_TIP_AUTOVEHICULE);
    }

    @Test
    @Transactional
    public void getAllDealersByTipAutovehiculeIsInShouldWork() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where tipAutovehicule in DEFAULT_TIP_AUTOVEHICULE or UPDATED_TIP_AUTOVEHICULE
        defaultDealerShouldBeFound("tipAutovehicule.in=" + DEFAULT_TIP_AUTOVEHICULE + "," + UPDATED_TIP_AUTOVEHICULE);

        // Get all the dealerList where tipAutovehicule equals to UPDATED_TIP_AUTOVEHICULE
        defaultDealerShouldNotBeFound("tipAutovehicule.in=" + UPDATED_TIP_AUTOVEHICULE);
    }

    @Test
    @Transactional
    public void getAllDealersByTipAutovehiculeIsNullOrNotNull() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where tipAutovehicule is not null
        defaultDealerShouldBeFound("tipAutovehicule.specified=true");

        // Get all the dealerList where tipAutovehicule is null
        defaultDealerShouldNotBeFound("tipAutovehicule.specified=false");
    }

    @Test
    @Transactional
    public void getAllDealersByDealerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where dealerId equals to DEFAULT_DEALER_ID
        defaultDealerShouldBeFound("dealerId.equals=" + DEFAULT_DEALER_ID);

        // Get all the dealerList where dealerId equals to UPDATED_DEALER_ID
        defaultDealerShouldNotBeFound("dealerId.equals=" + UPDATED_DEALER_ID);
    }

    @Test
    @Transactional
    public void getAllDealersByDealerIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where dealerId not equals to DEFAULT_DEALER_ID
        defaultDealerShouldNotBeFound("dealerId.notEquals=" + DEFAULT_DEALER_ID);

        // Get all the dealerList where dealerId not equals to UPDATED_DEALER_ID
        defaultDealerShouldBeFound("dealerId.notEquals=" + UPDATED_DEALER_ID);
    }

    @Test
    @Transactional
    public void getAllDealersByDealerIdIsInShouldWork() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where dealerId in DEFAULT_DEALER_ID or UPDATED_DEALER_ID
        defaultDealerShouldBeFound("dealerId.in=" + DEFAULT_DEALER_ID + "," + UPDATED_DEALER_ID);

        // Get all the dealerList where dealerId equals to UPDATED_DEALER_ID
        defaultDealerShouldNotBeFound("dealerId.in=" + UPDATED_DEALER_ID);
    }

    @Test
    @Transactional
    public void getAllDealersByDealerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where dealerId is not null
        defaultDealerShouldBeFound("dealerId.specified=true");

        // Get all the dealerList where dealerId is null
        defaultDealerShouldNotBeFound("dealerId.specified=false");
    }
                @Test
    @Transactional
    public void getAllDealersByDealerIdContainsSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where dealerId contains DEFAULT_DEALER_ID
        defaultDealerShouldBeFound("dealerId.contains=" + DEFAULT_DEALER_ID);

        // Get all the dealerList where dealerId contains UPDATED_DEALER_ID
        defaultDealerShouldNotBeFound("dealerId.contains=" + UPDATED_DEALER_ID);
    }

    @Test
    @Transactional
    public void getAllDealersByDealerIdNotContainsSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        // Get all the dealerList where dealerId does not contain DEFAULT_DEALER_ID
        defaultDealerShouldNotBeFound("dealerId.doesNotContain=" + DEFAULT_DEALER_ID);

        // Get all the dealerList where dealerId does not contain UPDATED_DEALER_ID
        defaultDealerShouldBeFound("dealerId.doesNotContain=" + UPDATED_DEALER_ID);
    }


    @Test
    @Transactional
    public void getAllDealersByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        dealer.addUser(user);
        dealerRepository.saveAndFlush(dealer);
        Long userId = user.getId();

        // Get all the dealerList where user equals to userId
        defaultDealerShouldBeFound("userId.equals=" + userId);

        // Get all the dealerList where user equals to userId + 1
        defaultDealerShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllDealersByStocIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);
        Stoc stoc = StocResourceIT.createEntity(em);
        em.persist(stoc);
        em.flush();
        dealer.setStoc(stoc);
        dealerRepository.saveAndFlush(dealer);
        Long stocId = stoc.getId();

        // Get all the dealerList where stoc equals to stocId
        defaultDealerShouldBeFound("stocId.equals=" + stocId);

        // Get all the dealerList where stoc equals to stocId + 1
        defaultDealerShouldNotBeFound("stocId.equals=" + (stocId + 1));
    }


    @Test
    @Transactional
    public void getAllDealersByPortofoliuIsEqualToSomething() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);
        Portofoliu portofoliu = PortofoliuResourceIT.createEntity(em);
        em.persist(portofoliu);
        em.flush();
        dealer.setPortofoliu(portofoliu);
        dealerRepository.saveAndFlush(dealer);
        Long portofoliuId = portofoliu.getId();

        // Get all the dealerList where portofoliu equals to portofoliuId
        defaultDealerShouldBeFound("portofoliuId.equals=" + portofoliuId);

        // Get all the dealerList where portofoliu equals to portofoliuId + 1
        defaultDealerShouldNotBeFound("portofoliuId.equals=" + (portofoliuId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDealerShouldBeFound(String filter) throws Exception {
        restDealerMockMvc.perform(get("/api/dealers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tipAutovehicule").value(hasItem(DEFAULT_TIP_AUTOVEHICULE.toString())))
            .andExpect(jsonPath("$.[*].dealerId").value(hasItem(DEFAULT_DEALER_ID)));

        // Check, that the count call also returns 1
        restDealerMockMvc.perform(get("/api/dealers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDealerShouldNotBeFound(String filter) throws Exception {
        restDealerMockMvc.perform(get("/api/dealers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDealerMockMvc.perform(get("/api/dealers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDealer() throws Exception {
        // Get the dealer
        restDealerMockMvc.perform(get("/api/dealers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealer() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        int databaseSizeBeforeUpdate = dealerRepository.findAll().size();

        // Update the dealer
        Dealer updatedDealer = dealerRepository.findById(dealer.getId()).get();
        // Disconnect from session so that the updates on updatedDealer are not directly saved in db
        em.detach(updatedDealer);
        updatedDealer
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .tipAutovehicule(UPDATED_TIP_AUTOVEHICULE)
            .dealerId(UPDATED_DEALER_ID);
        DealerDTO dealerDTO = dealerMapper.toDto(updatedDealer);

        restDealerMockMvc.perform(put("/api/dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerDTO)))
            .andExpect(status().isOk());

        // Validate the Dealer in the database
        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeUpdate);
        Dealer testDealer = dealerList.get(dealerList.size() - 1);
        assertThat(testDealer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDealer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDealer.getTipAutovehicule()).isEqualTo(UPDATED_TIP_AUTOVEHICULE);
        assertThat(testDealer.getDealerId()).isEqualTo(UPDATED_DEALER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingDealer() throws Exception {
        int databaseSizeBeforeUpdate = dealerRepository.findAll().size();

        // Create the Dealer
        DealerDTO dealerDTO = dealerMapper.toDto(dealer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealerMockMvc.perform(put("/api/dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dealer in the database
        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealer() throws Exception {
        // Initialize the database
        dealerRepository.saveAndFlush(dealer);

        int databaseSizeBeforeDelete = dealerRepository.findAll().size();

        // Delete the dealer
        restDealerMockMvc.perform(delete("/api/dealers/{id}", dealer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dealer> dealerList = dealerRepository.findAll();
        assertThat(dealerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

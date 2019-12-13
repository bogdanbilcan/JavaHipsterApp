package com.bbc.myapp.web.rest;

import com.bbc.myapp.JavaHipsterApp;
import com.bbc.myapp.domain.Stoc;
import com.bbc.myapp.domain.Dealer;
import com.bbc.myapp.repository.StocRepository;
import com.bbc.myapp.service.StocService;
import com.bbc.myapp.service.dto.StocDTO;
import com.bbc.myapp.service.mapper.StocMapper;
import com.bbc.myapp.web.rest.errors.ExceptionTranslator;
import com.bbc.myapp.service.dto.StocCriteria;
import com.bbc.myapp.service.StocQueryService;

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
 * Integration tests for the {@link StocResource} REST controller.
 */
@SpringBootTest(classes = JavaHipsterApp.class)
public class StocResourceIT {

    private static final Integer DEFAULT_HTROCARNO = 1;
    private static final Integer UPDATED_HTROCARNO = 2;
    private static final Integer SMALLER_HTROCARNO = 1 - 1;

    private static final Integer DEFAULT_RESDEALERID = 1;
    private static final Integer UPDATED_RESDEALERID = 2;
    private static final Integer SMALLER_RESDEALERID = 1 - 1;

    private static final Integer DEFAULT_ANFABRICATIECIV = 1;
    private static final Integer UPDATED_ANFABRICATIECIV = 2;
    private static final Integer SMALLER_ANFABRICATIECIV = 1 - 1;

    private static final String DEFAULT_TIPAUTOVEHICUL = "AAAAAAAAAA";
    private static final String UPDATED_TIPAUTOVEHICUL = "BBBBBBBBBB";

    private static final String DEFAULT_CODCULOAREEXTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_CODCULOAREEXTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCCULOAREEXTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_DESCCULOAREEXTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_VOPSEAMETALIZATA = "AAAAAAAAAA";
    private static final String UPDATED_VOPSEAMETALIZATA = "BBBBBBBBBB";

    private static final String DEFAULT_CULOAREINTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_CULOAREINTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATII = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATII = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATIE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIE = "BBBBBBBBBB";

    private static final String DEFAULT_OMOLOGAREIND = "AAAAAAAAAA";
    private static final String UPDATED_OMOLOGAREIND = "BBBBBBBBBB";

    private static final String DEFAULT_LUNASOSIREINTARA = "AAAAAAAAAA";
    private static final String UPDATED_LUNASOSIREINTARA = "BBBBBBBBBB";

    private static final String DEFAULT_REZERVATA = "AAAAAAAAAA";
    private static final String UPDATED_REZERVATA = "BBBBBBBBBB";

    private static final String DEFAULT_DATAEXPIRAREREZ = "AAAAAAAAAA";
    private static final String UPDATED_DATAEXPIRAREREZ = "BBBBBBBBBB";

    @Autowired
    private StocRepository stocRepository;

    @Autowired
    private StocMapper stocMapper;

    @Autowired
    private StocService stocService;

    @Autowired
    private StocQueryService stocQueryService;

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

    private MockMvc restStocMockMvc;

    private Stoc stoc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StocResource stocResource = new StocResource(stocService, stocQueryService);
        this.restStocMockMvc = MockMvcBuilders.standaloneSetup(stocResource)
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
    public static Stoc createEntity(EntityManager em) {
        Stoc stoc = new Stoc()
            .htrocarno(DEFAULT_HTROCARNO)
            .resdealerid(DEFAULT_RESDEALERID)
            .anfabricatieciv(DEFAULT_ANFABRICATIECIV)
            .tipautovehicul(DEFAULT_TIPAUTOVEHICUL)
            .codculoareexterior(DEFAULT_CODCULOAREEXTERIOR)
            .descculoareexterior(DEFAULT_DESCCULOAREEXTERIOR)
            .vopseametalizata(DEFAULT_VOPSEAMETALIZATA)
            .culoareinterior(DEFAULT_CULOAREINTERIOR)
            .observatii(DEFAULT_OBSERVATII)
            .locatie(DEFAULT_LOCATIE)
            .omologareind(DEFAULT_OMOLOGAREIND)
            .lunasosireintara(DEFAULT_LUNASOSIREINTARA)
            .rezervata(DEFAULT_REZERVATA)
            .dataexpirarerez(DEFAULT_DATAEXPIRAREREZ);
        return stoc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stoc createUpdatedEntity(EntityManager em) {
        Stoc stoc = new Stoc()
            .htrocarno(UPDATED_HTROCARNO)
            .resdealerid(UPDATED_RESDEALERID)
            .anfabricatieciv(UPDATED_ANFABRICATIECIV)
            .tipautovehicul(UPDATED_TIPAUTOVEHICUL)
            .codculoareexterior(UPDATED_CODCULOAREEXTERIOR)
            .descculoareexterior(UPDATED_DESCCULOAREEXTERIOR)
            .vopseametalizata(UPDATED_VOPSEAMETALIZATA)
            .culoareinterior(UPDATED_CULOAREINTERIOR)
            .observatii(UPDATED_OBSERVATII)
            .locatie(UPDATED_LOCATIE)
            .omologareind(UPDATED_OMOLOGAREIND)
            .lunasosireintara(UPDATED_LUNASOSIREINTARA)
            .rezervata(UPDATED_REZERVATA)
            .dataexpirarerez(UPDATED_DATAEXPIRAREREZ);
        return stoc;
    }

    @BeforeEach
    public void initTest() {
        stoc = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllStocs() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList
        restStocMockMvc.perform(get("/api/stocs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].htrocarno").value(hasItem(DEFAULT_HTROCARNO)))
            .andExpect(jsonPath("$.[*].resdealerid").value(hasItem(DEFAULT_RESDEALERID)))
            .andExpect(jsonPath("$.[*].anfabricatieciv").value(hasItem(DEFAULT_ANFABRICATIECIV)))
            .andExpect(jsonPath("$.[*].tipautovehicul").value(hasItem(DEFAULT_TIPAUTOVEHICUL)))
            .andExpect(jsonPath("$.[*].codculoareexterior").value(hasItem(DEFAULT_CODCULOAREEXTERIOR)))
            .andExpect(jsonPath("$.[*].descculoareexterior").value(hasItem(DEFAULT_DESCCULOAREEXTERIOR)))
            .andExpect(jsonPath("$.[*].vopseametalizata").value(hasItem(DEFAULT_VOPSEAMETALIZATA)))
            .andExpect(jsonPath("$.[*].culoareinterior").value(hasItem(DEFAULT_CULOAREINTERIOR)))
            .andExpect(jsonPath("$.[*].observatii").value(hasItem(DEFAULT_OBSERVATII)))
            .andExpect(jsonPath("$.[*].locatie").value(hasItem(DEFAULT_LOCATIE)))
            .andExpect(jsonPath("$.[*].omologareind").value(hasItem(DEFAULT_OMOLOGAREIND)))
            .andExpect(jsonPath("$.[*].lunasosireintara").value(hasItem(DEFAULT_LUNASOSIREINTARA)))
            .andExpect(jsonPath("$.[*].rezervata").value(hasItem(DEFAULT_REZERVATA)))
            .andExpect(jsonPath("$.[*].dataexpirarerez").value(hasItem(DEFAULT_DATAEXPIRAREREZ)));
    }
    
    @Test
    @Transactional
    public void getStoc() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get the stoc
        restStocMockMvc.perform(get("/api/stocs/{id}", stoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stoc.getId().intValue()))
            .andExpect(jsonPath("$.htrocarno").value(DEFAULT_HTROCARNO))
            .andExpect(jsonPath("$.resdealerid").value(DEFAULT_RESDEALERID))
            .andExpect(jsonPath("$.anfabricatieciv").value(DEFAULT_ANFABRICATIECIV))
            .andExpect(jsonPath("$.tipautovehicul").value(DEFAULT_TIPAUTOVEHICUL))
            .andExpect(jsonPath("$.codculoareexterior").value(DEFAULT_CODCULOAREEXTERIOR))
            .andExpect(jsonPath("$.descculoareexterior").value(DEFAULT_DESCCULOAREEXTERIOR))
            .andExpect(jsonPath("$.vopseametalizata").value(DEFAULT_VOPSEAMETALIZATA))
            .andExpect(jsonPath("$.culoareinterior").value(DEFAULT_CULOAREINTERIOR))
            .andExpect(jsonPath("$.observatii").value(DEFAULT_OBSERVATII))
            .andExpect(jsonPath("$.locatie").value(DEFAULT_LOCATIE))
            .andExpect(jsonPath("$.omologareind").value(DEFAULT_OMOLOGAREIND))
            .andExpect(jsonPath("$.lunasosireintara").value(DEFAULT_LUNASOSIREINTARA))
            .andExpect(jsonPath("$.rezervata").value(DEFAULT_REZERVATA))
            .andExpect(jsonPath("$.dataexpirarerez").value(DEFAULT_DATAEXPIRAREREZ));
    }


    @Test
    @Transactional
    public void getStocsByIdFiltering() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        Long id = stoc.getId();

        defaultStocShouldBeFound("id.equals=" + id);
        defaultStocShouldNotBeFound("id.notEquals=" + id);

        defaultStocShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultStocShouldNotBeFound("id.greaterThan=" + id);

        defaultStocShouldBeFound("id.lessThanOrEqual=" + id);
        defaultStocShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno equals to DEFAULT_HTROCARNO
        defaultStocShouldBeFound("htrocarno.equals=" + DEFAULT_HTROCARNO);

        // Get all the stocList where htrocarno equals to UPDATED_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.equals=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno not equals to DEFAULT_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.notEquals=" + DEFAULT_HTROCARNO);

        // Get all the stocList where htrocarno not equals to UPDATED_HTROCARNO
        defaultStocShouldBeFound("htrocarno.notEquals=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno in DEFAULT_HTROCARNO or UPDATED_HTROCARNO
        defaultStocShouldBeFound("htrocarno.in=" + DEFAULT_HTROCARNO + "," + UPDATED_HTROCARNO);

        // Get all the stocList where htrocarno equals to UPDATED_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.in=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno is not null
        defaultStocShouldBeFound("htrocarno.specified=true");

        // Get all the stocList where htrocarno is null
        defaultStocShouldNotBeFound("htrocarno.specified=false");
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno is greater than or equal to DEFAULT_HTROCARNO
        defaultStocShouldBeFound("htrocarno.greaterThanOrEqual=" + DEFAULT_HTROCARNO);

        // Get all the stocList where htrocarno is greater than or equal to UPDATED_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.greaterThanOrEqual=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno is less than or equal to DEFAULT_HTROCARNO
        defaultStocShouldBeFound("htrocarno.lessThanOrEqual=" + DEFAULT_HTROCARNO);

        // Get all the stocList where htrocarno is less than or equal to SMALLER_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.lessThanOrEqual=" + SMALLER_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsLessThanSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno is less than DEFAULT_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.lessThan=" + DEFAULT_HTROCARNO);

        // Get all the stocList where htrocarno is less than UPDATED_HTROCARNO
        defaultStocShouldBeFound("htrocarno.lessThan=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllStocsByHtrocarnoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where htrocarno is greater than DEFAULT_HTROCARNO
        defaultStocShouldNotBeFound("htrocarno.greaterThan=" + DEFAULT_HTROCARNO);

        // Get all the stocList where htrocarno is greater than SMALLER_HTROCARNO
        defaultStocShouldBeFound("htrocarno.greaterThan=" + SMALLER_HTROCARNO);
    }


    @Test
    @Transactional
    public void getAllStocsByResdealeridIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid equals to DEFAULT_RESDEALERID
        defaultStocShouldBeFound("resdealerid.equals=" + DEFAULT_RESDEALERID);

        // Get all the stocList where resdealerid equals to UPDATED_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.equals=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid not equals to DEFAULT_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.notEquals=" + DEFAULT_RESDEALERID);

        // Get all the stocList where resdealerid not equals to UPDATED_RESDEALERID
        defaultStocShouldBeFound("resdealerid.notEquals=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid in DEFAULT_RESDEALERID or UPDATED_RESDEALERID
        defaultStocShouldBeFound("resdealerid.in=" + DEFAULT_RESDEALERID + "," + UPDATED_RESDEALERID);

        // Get all the stocList where resdealerid equals to UPDATED_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.in=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid is not null
        defaultStocShouldBeFound("resdealerid.specified=true");

        // Get all the stocList where resdealerid is null
        defaultStocShouldNotBeFound("resdealerid.specified=false");
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid is greater than or equal to DEFAULT_RESDEALERID
        defaultStocShouldBeFound("resdealerid.greaterThanOrEqual=" + DEFAULT_RESDEALERID);

        // Get all the stocList where resdealerid is greater than or equal to UPDATED_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.greaterThanOrEqual=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid is less than or equal to DEFAULT_RESDEALERID
        defaultStocShouldBeFound("resdealerid.lessThanOrEqual=" + DEFAULT_RESDEALERID);

        // Get all the stocList where resdealerid is less than or equal to SMALLER_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.lessThanOrEqual=" + SMALLER_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsLessThanSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid is less than DEFAULT_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.lessThan=" + DEFAULT_RESDEALERID);

        // Get all the stocList where resdealerid is less than UPDATED_RESDEALERID
        defaultStocShouldBeFound("resdealerid.lessThan=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllStocsByResdealeridIsGreaterThanSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where resdealerid is greater than DEFAULT_RESDEALERID
        defaultStocShouldNotBeFound("resdealerid.greaterThan=" + DEFAULT_RESDEALERID);

        // Get all the stocList where resdealerid is greater than SMALLER_RESDEALERID
        defaultStocShouldBeFound("resdealerid.greaterThan=" + SMALLER_RESDEALERID);
    }


    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv equals to DEFAULT_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.equals=" + DEFAULT_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv equals to UPDATED_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.equals=" + UPDATED_ANFABRICATIECIV);
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv not equals to DEFAULT_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.notEquals=" + DEFAULT_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv not equals to UPDATED_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.notEquals=" + UPDATED_ANFABRICATIECIV);
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv in DEFAULT_ANFABRICATIECIV or UPDATED_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.in=" + DEFAULT_ANFABRICATIECIV + "," + UPDATED_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv equals to UPDATED_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.in=" + UPDATED_ANFABRICATIECIV);
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv is not null
        defaultStocShouldBeFound("anfabricatieciv.specified=true");

        // Get all the stocList where anfabricatieciv is null
        defaultStocShouldNotBeFound("anfabricatieciv.specified=false");
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv is greater than or equal to DEFAULT_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.greaterThanOrEqual=" + DEFAULT_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv is greater than or equal to UPDATED_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.greaterThanOrEqual=" + UPDATED_ANFABRICATIECIV);
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv is less than or equal to DEFAULT_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.lessThanOrEqual=" + DEFAULT_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv is less than or equal to SMALLER_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.lessThanOrEqual=" + SMALLER_ANFABRICATIECIV);
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsLessThanSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv is less than DEFAULT_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.lessThan=" + DEFAULT_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv is less than UPDATED_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.lessThan=" + UPDATED_ANFABRICATIECIV);
    }

    @Test
    @Transactional
    public void getAllStocsByAnfabricatiecivIsGreaterThanSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where anfabricatieciv is greater than DEFAULT_ANFABRICATIECIV
        defaultStocShouldNotBeFound("anfabricatieciv.greaterThan=" + DEFAULT_ANFABRICATIECIV);

        // Get all the stocList where anfabricatieciv is greater than SMALLER_ANFABRICATIECIV
        defaultStocShouldBeFound("anfabricatieciv.greaterThan=" + SMALLER_ANFABRICATIECIV);
    }


    @Test
    @Transactional
    public void getAllStocsByTipautovehiculIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where tipautovehicul equals to DEFAULT_TIPAUTOVEHICUL
        defaultStocShouldBeFound("tipautovehicul.equals=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the stocList where tipautovehicul equals to UPDATED_TIPAUTOVEHICUL
        defaultStocShouldNotBeFound("tipautovehicul.equals=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllStocsByTipautovehiculIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where tipautovehicul not equals to DEFAULT_TIPAUTOVEHICUL
        defaultStocShouldNotBeFound("tipautovehicul.notEquals=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the stocList where tipautovehicul not equals to UPDATED_TIPAUTOVEHICUL
        defaultStocShouldBeFound("tipautovehicul.notEquals=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllStocsByTipautovehiculIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where tipautovehicul in DEFAULT_TIPAUTOVEHICUL or UPDATED_TIPAUTOVEHICUL
        defaultStocShouldBeFound("tipautovehicul.in=" + DEFAULT_TIPAUTOVEHICUL + "," + UPDATED_TIPAUTOVEHICUL);

        // Get all the stocList where tipautovehicul equals to UPDATED_TIPAUTOVEHICUL
        defaultStocShouldNotBeFound("tipautovehicul.in=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllStocsByTipautovehiculIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where tipautovehicul is not null
        defaultStocShouldBeFound("tipautovehicul.specified=true");

        // Get all the stocList where tipautovehicul is null
        defaultStocShouldNotBeFound("tipautovehicul.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByTipautovehiculContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where tipautovehicul contains DEFAULT_TIPAUTOVEHICUL
        defaultStocShouldBeFound("tipautovehicul.contains=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the stocList where tipautovehicul contains UPDATED_TIPAUTOVEHICUL
        defaultStocShouldNotBeFound("tipautovehicul.contains=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllStocsByTipautovehiculNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where tipautovehicul does not contain DEFAULT_TIPAUTOVEHICUL
        defaultStocShouldNotBeFound("tipautovehicul.doesNotContain=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the stocList where tipautovehicul does not contain UPDATED_TIPAUTOVEHICUL
        defaultStocShouldBeFound("tipautovehicul.doesNotContain=" + UPDATED_TIPAUTOVEHICUL);
    }


    @Test
    @Transactional
    public void getAllStocsByCodculoareexteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where codculoareexterior equals to DEFAULT_CODCULOAREEXTERIOR
        defaultStocShouldBeFound("codculoareexterior.equals=" + DEFAULT_CODCULOAREEXTERIOR);

        // Get all the stocList where codculoareexterior equals to UPDATED_CODCULOAREEXTERIOR
        defaultStocShouldNotBeFound("codculoareexterior.equals=" + UPDATED_CODCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCodculoareexteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where codculoareexterior not equals to DEFAULT_CODCULOAREEXTERIOR
        defaultStocShouldNotBeFound("codculoareexterior.notEquals=" + DEFAULT_CODCULOAREEXTERIOR);

        // Get all the stocList where codculoareexterior not equals to UPDATED_CODCULOAREEXTERIOR
        defaultStocShouldBeFound("codculoareexterior.notEquals=" + UPDATED_CODCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCodculoareexteriorIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where codculoareexterior in DEFAULT_CODCULOAREEXTERIOR or UPDATED_CODCULOAREEXTERIOR
        defaultStocShouldBeFound("codculoareexterior.in=" + DEFAULT_CODCULOAREEXTERIOR + "," + UPDATED_CODCULOAREEXTERIOR);

        // Get all the stocList where codculoareexterior equals to UPDATED_CODCULOAREEXTERIOR
        defaultStocShouldNotBeFound("codculoareexterior.in=" + UPDATED_CODCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCodculoareexteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where codculoareexterior is not null
        defaultStocShouldBeFound("codculoareexterior.specified=true");

        // Get all the stocList where codculoareexterior is null
        defaultStocShouldNotBeFound("codculoareexterior.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByCodculoareexteriorContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where codculoareexterior contains DEFAULT_CODCULOAREEXTERIOR
        defaultStocShouldBeFound("codculoareexterior.contains=" + DEFAULT_CODCULOAREEXTERIOR);

        // Get all the stocList where codculoareexterior contains UPDATED_CODCULOAREEXTERIOR
        defaultStocShouldNotBeFound("codculoareexterior.contains=" + UPDATED_CODCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCodculoareexteriorNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where codculoareexterior does not contain DEFAULT_CODCULOAREEXTERIOR
        defaultStocShouldNotBeFound("codculoareexterior.doesNotContain=" + DEFAULT_CODCULOAREEXTERIOR);

        // Get all the stocList where codculoareexterior does not contain UPDATED_CODCULOAREEXTERIOR
        defaultStocShouldBeFound("codculoareexterior.doesNotContain=" + UPDATED_CODCULOAREEXTERIOR);
    }


    @Test
    @Transactional
    public void getAllStocsByDescculoareexteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where descculoareexterior equals to DEFAULT_DESCCULOAREEXTERIOR
        defaultStocShouldBeFound("descculoareexterior.equals=" + DEFAULT_DESCCULOAREEXTERIOR);

        // Get all the stocList where descculoareexterior equals to UPDATED_DESCCULOAREEXTERIOR
        defaultStocShouldNotBeFound("descculoareexterior.equals=" + UPDATED_DESCCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByDescculoareexteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where descculoareexterior not equals to DEFAULT_DESCCULOAREEXTERIOR
        defaultStocShouldNotBeFound("descculoareexterior.notEquals=" + DEFAULT_DESCCULOAREEXTERIOR);

        // Get all the stocList where descculoareexterior not equals to UPDATED_DESCCULOAREEXTERIOR
        defaultStocShouldBeFound("descculoareexterior.notEquals=" + UPDATED_DESCCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByDescculoareexteriorIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where descculoareexterior in DEFAULT_DESCCULOAREEXTERIOR or UPDATED_DESCCULOAREEXTERIOR
        defaultStocShouldBeFound("descculoareexterior.in=" + DEFAULT_DESCCULOAREEXTERIOR + "," + UPDATED_DESCCULOAREEXTERIOR);

        // Get all the stocList where descculoareexterior equals to UPDATED_DESCCULOAREEXTERIOR
        defaultStocShouldNotBeFound("descculoareexterior.in=" + UPDATED_DESCCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByDescculoareexteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where descculoareexterior is not null
        defaultStocShouldBeFound("descculoareexterior.specified=true");

        // Get all the stocList where descculoareexterior is null
        defaultStocShouldNotBeFound("descculoareexterior.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByDescculoareexteriorContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where descculoareexterior contains DEFAULT_DESCCULOAREEXTERIOR
        defaultStocShouldBeFound("descculoareexterior.contains=" + DEFAULT_DESCCULOAREEXTERIOR);

        // Get all the stocList where descculoareexterior contains UPDATED_DESCCULOAREEXTERIOR
        defaultStocShouldNotBeFound("descculoareexterior.contains=" + UPDATED_DESCCULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByDescculoareexteriorNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where descculoareexterior does not contain DEFAULT_DESCCULOAREEXTERIOR
        defaultStocShouldNotBeFound("descculoareexterior.doesNotContain=" + DEFAULT_DESCCULOAREEXTERIOR);

        // Get all the stocList where descculoareexterior does not contain UPDATED_DESCCULOAREEXTERIOR
        defaultStocShouldBeFound("descculoareexterior.doesNotContain=" + UPDATED_DESCCULOAREEXTERIOR);
    }


    @Test
    @Transactional
    public void getAllStocsByVopseametalizataIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where vopseametalizata equals to DEFAULT_VOPSEAMETALIZATA
        defaultStocShouldBeFound("vopseametalizata.equals=" + DEFAULT_VOPSEAMETALIZATA);

        // Get all the stocList where vopseametalizata equals to UPDATED_VOPSEAMETALIZATA
        defaultStocShouldNotBeFound("vopseametalizata.equals=" + UPDATED_VOPSEAMETALIZATA);
    }

    @Test
    @Transactional
    public void getAllStocsByVopseametalizataIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where vopseametalizata not equals to DEFAULT_VOPSEAMETALIZATA
        defaultStocShouldNotBeFound("vopseametalizata.notEquals=" + DEFAULT_VOPSEAMETALIZATA);

        // Get all the stocList where vopseametalizata not equals to UPDATED_VOPSEAMETALIZATA
        defaultStocShouldBeFound("vopseametalizata.notEquals=" + UPDATED_VOPSEAMETALIZATA);
    }

    @Test
    @Transactional
    public void getAllStocsByVopseametalizataIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where vopseametalizata in DEFAULT_VOPSEAMETALIZATA or UPDATED_VOPSEAMETALIZATA
        defaultStocShouldBeFound("vopseametalizata.in=" + DEFAULT_VOPSEAMETALIZATA + "," + UPDATED_VOPSEAMETALIZATA);

        // Get all the stocList where vopseametalizata equals to UPDATED_VOPSEAMETALIZATA
        defaultStocShouldNotBeFound("vopseametalizata.in=" + UPDATED_VOPSEAMETALIZATA);
    }

    @Test
    @Transactional
    public void getAllStocsByVopseametalizataIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where vopseametalizata is not null
        defaultStocShouldBeFound("vopseametalizata.specified=true");

        // Get all the stocList where vopseametalizata is null
        defaultStocShouldNotBeFound("vopseametalizata.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByVopseametalizataContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where vopseametalizata contains DEFAULT_VOPSEAMETALIZATA
        defaultStocShouldBeFound("vopseametalizata.contains=" + DEFAULT_VOPSEAMETALIZATA);

        // Get all the stocList where vopseametalizata contains UPDATED_VOPSEAMETALIZATA
        defaultStocShouldNotBeFound("vopseametalizata.contains=" + UPDATED_VOPSEAMETALIZATA);
    }

    @Test
    @Transactional
    public void getAllStocsByVopseametalizataNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where vopseametalizata does not contain DEFAULT_VOPSEAMETALIZATA
        defaultStocShouldNotBeFound("vopseametalizata.doesNotContain=" + DEFAULT_VOPSEAMETALIZATA);

        // Get all the stocList where vopseametalizata does not contain UPDATED_VOPSEAMETALIZATA
        defaultStocShouldBeFound("vopseametalizata.doesNotContain=" + UPDATED_VOPSEAMETALIZATA);
    }


    @Test
    @Transactional
    public void getAllStocsByCuloareinteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where culoareinterior equals to DEFAULT_CULOAREINTERIOR
        defaultStocShouldBeFound("culoareinterior.equals=" + DEFAULT_CULOAREINTERIOR);

        // Get all the stocList where culoareinterior equals to UPDATED_CULOAREINTERIOR
        defaultStocShouldNotBeFound("culoareinterior.equals=" + UPDATED_CULOAREINTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCuloareinteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where culoareinterior not equals to DEFAULT_CULOAREINTERIOR
        defaultStocShouldNotBeFound("culoareinterior.notEquals=" + DEFAULT_CULOAREINTERIOR);

        // Get all the stocList where culoareinterior not equals to UPDATED_CULOAREINTERIOR
        defaultStocShouldBeFound("culoareinterior.notEquals=" + UPDATED_CULOAREINTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCuloareinteriorIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where culoareinterior in DEFAULT_CULOAREINTERIOR or UPDATED_CULOAREINTERIOR
        defaultStocShouldBeFound("culoareinterior.in=" + DEFAULT_CULOAREINTERIOR + "," + UPDATED_CULOAREINTERIOR);

        // Get all the stocList where culoareinterior equals to UPDATED_CULOAREINTERIOR
        defaultStocShouldNotBeFound("culoareinterior.in=" + UPDATED_CULOAREINTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCuloareinteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where culoareinterior is not null
        defaultStocShouldBeFound("culoareinterior.specified=true");

        // Get all the stocList where culoareinterior is null
        defaultStocShouldNotBeFound("culoareinterior.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByCuloareinteriorContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where culoareinterior contains DEFAULT_CULOAREINTERIOR
        defaultStocShouldBeFound("culoareinterior.contains=" + DEFAULT_CULOAREINTERIOR);

        // Get all the stocList where culoareinterior contains UPDATED_CULOAREINTERIOR
        defaultStocShouldNotBeFound("culoareinterior.contains=" + UPDATED_CULOAREINTERIOR);
    }

    @Test
    @Transactional
    public void getAllStocsByCuloareinteriorNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where culoareinterior does not contain DEFAULT_CULOAREINTERIOR
        defaultStocShouldNotBeFound("culoareinterior.doesNotContain=" + DEFAULT_CULOAREINTERIOR);

        // Get all the stocList where culoareinterior does not contain UPDATED_CULOAREINTERIOR
        defaultStocShouldBeFound("culoareinterior.doesNotContain=" + UPDATED_CULOAREINTERIOR);
    }


    @Test
    @Transactional
    public void getAllStocsByObservatiiIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where observatii equals to DEFAULT_OBSERVATII
        defaultStocShouldBeFound("observatii.equals=" + DEFAULT_OBSERVATII);

        // Get all the stocList where observatii equals to UPDATED_OBSERVATII
        defaultStocShouldNotBeFound("observatii.equals=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllStocsByObservatiiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where observatii not equals to DEFAULT_OBSERVATII
        defaultStocShouldNotBeFound("observatii.notEquals=" + DEFAULT_OBSERVATII);

        // Get all the stocList where observatii not equals to UPDATED_OBSERVATII
        defaultStocShouldBeFound("observatii.notEquals=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllStocsByObservatiiIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where observatii in DEFAULT_OBSERVATII or UPDATED_OBSERVATII
        defaultStocShouldBeFound("observatii.in=" + DEFAULT_OBSERVATII + "," + UPDATED_OBSERVATII);

        // Get all the stocList where observatii equals to UPDATED_OBSERVATII
        defaultStocShouldNotBeFound("observatii.in=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllStocsByObservatiiIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where observatii is not null
        defaultStocShouldBeFound("observatii.specified=true");

        // Get all the stocList where observatii is null
        defaultStocShouldNotBeFound("observatii.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByObservatiiContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where observatii contains DEFAULT_OBSERVATII
        defaultStocShouldBeFound("observatii.contains=" + DEFAULT_OBSERVATII);

        // Get all the stocList where observatii contains UPDATED_OBSERVATII
        defaultStocShouldNotBeFound("observatii.contains=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllStocsByObservatiiNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where observatii does not contain DEFAULT_OBSERVATII
        defaultStocShouldNotBeFound("observatii.doesNotContain=" + DEFAULT_OBSERVATII);

        // Get all the stocList where observatii does not contain UPDATED_OBSERVATII
        defaultStocShouldBeFound("observatii.doesNotContain=" + UPDATED_OBSERVATII);
    }


    @Test
    @Transactional
    public void getAllStocsByLocatieIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where locatie equals to DEFAULT_LOCATIE
        defaultStocShouldBeFound("locatie.equals=" + DEFAULT_LOCATIE);

        // Get all the stocList where locatie equals to UPDATED_LOCATIE
        defaultStocShouldNotBeFound("locatie.equals=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllStocsByLocatieIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where locatie not equals to DEFAULT_LOCATIE
        defaultStocShouldNotBeFound("locatie.notEquals=" + DEFAULT_LOCATIE);

        // Get all the stocList where locatie not equals to UPDATED_LOCATIE
        defaultStocShouldBeFound("locatie.notEquals=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllStocsByLocatieIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where locatie in DEFAULT_LOCATIE or UPDATED_LOCATIE
        defaultStocShouldBeFound("locatie.in=" + DEFAULT_LOCATIE + "," + UPDATED_LOCATIE);

        // Get all the stocList where locatie equals to UPDATED_LOCATIE
        defaultStocShouldNotBeFound("locatie.in=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllStocsByLocatieIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where locatie is not null
        defaultStocShouldBeFound("locatie.specified=true");

        // Get all the stocList where locatie is null
        defaultStocShouldNotBeFound("locatie.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByLocatieContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where locatie contains DEFAULT_LOCATIE
        defaultStocShouldBeFound("locatie.contains=" + DEFAULT_LOCATIE);

        // Get all the stocList where locatie contains UPDATED_LOCATIE
        defaultStocShouldNotBeFound("locatie.contains=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllStocsByLocatieNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where locatie does not contain DEFAULT_LOCATIE
        defaultStocShouldNotBeFound("locatie.doesNotContain=" + DEFAULT_LOCATIE);

        // Get all the stocList where locatie does not contain UPDATED_LOCATIE
        defaultStocShouldBeFound("locatie.doesNotContain=" + UPDATED_LOCATIE);
    }


    @Test
    @Transactional
    public void getAllStocsByOmologareindIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where omologareind equals to DEFAULT_OMOLOGAREIND
        defaultStocShouldBeFound("omologareind.equals=" + DEFAULT_OMOLOGAREIND);

        // Get all the stocList where omologareind equals to UPDATED_OMOLOGAREIND
        defaultStocShouldNotBeFound("omologareind.equals=" + UPDATED_OMOLOGAREIND);
    }

    @Test
    @Transactional
    public void getAllStocsByOmologareindIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where omologareind not equals to DEFAULT_OMOLOGAREIND
        defaultStocShouldNotBeFound("omologareind.notEquals=" + DEFAULT_OMOLOGAREIND);

        // Get all the stocList where omologareind not equals to UPDATED_OMOLOGAREIND
        defaultStocShouldBeFound("omologareind.notEquals=" + UPDATED_OMOLOGAREIND);
    }

    @Test
    @Transactional
    public void getAllStocsByOmologareindIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where omologareind in DEFAULT_OMOLOGAREIND or UPDATED_OMOLOGAREIND
        defaultStocShouldBeFound("omologareind.in=" + DEFAULT_OMOLOGAREIND + "," + UPDATED_OMOLOGAREIND);

        // Get all the stocList where omologareind equals to UPDATED_OMOLOGAREIND
        defaultStocShouldNotBeFound("omologareind.in=" + UPDATED_OMOLOGAREIND);
    }

    @Test
    @Transactional
    public void getAllStocsByOmologareindIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where omologareind is not null
        defaultStocShouldBeFound("omologareind.specified=true");

        // Get all the stocList where omologareind is null
        defaultStocShouldNotBeFound("omologareind.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByOmologareindContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where omologareind contains DEFAULT_OMOLOGAREIND
        defaultStocShouldBeFound("omologareind.contains=" + DEFAULT_OMOLOGAREIND);

        // Get all the stocList where omologareind contains UPDATED_OMOLOGAREIND
        defaultStocShouldNotBeFound("omologareind.contains=" + UPDATED_OMOLOGAREIND);
    }

    @Test
    @Transactional
    public void getAllStocsByOmologareindNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where omologareind does not contain DEFAULT_OMOLOGAREIND
        defaultStocShouldNotBeFound("omologareind.doesNotContain=" + DEFAULT_OMOLOGAREIND);

        // Get all the stocList where omologareind does not contain UPDATED_OMOLOGAREIND
        defaultStocShouldBeFound("omologareind.doesNotContain=" + UPDATED_OMOLOGAREIND);
    }


    @Test
    @Transactional
    public void getAllStocsByLunasosireintaraIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where lunasosireintara equals to DEFAULT_LUNASOSIREINTARA
        defaultStocShouldBeFound("lunasosireintara.equals=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the stocList where lunasosireintara equals to UPDATED_LUNASOSIREINTARA
        defaultStocShouldNotBeFound("lunasosireintara.equals=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllStocsByLunasosireintaraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where lunasosireintara not equals to DEFAULT_LUNASOSIREINTARA
        defaultStocShouldNotBeFound("lunasosireintara.notEquals=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the stocList where lunasosireintara not equals to UPDATED_LUNASOSIREINTARA
        defaultStocShouldBeFound("lunasosireintara.notEquals=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllStocsByLunasosireintaraIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where lunasosireintara in DEFAULT_LUNASOSIREINTARA or UPDATED_LUNASOSIREINTARA
        defaultStocShouldBeFound("lunasosireintara.in=" + DEFAULT_LUNASOSIREINTARA + "," + UPDATED_LUNASOSIREINTARA);

        // Get all the stocList where lunasosireintara equals to UPDATED_LUNASOSIREINTARA
        defaultStocShouldNotBeFound("lunasosireintara.in=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllStocsByLunasosireintaraIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where lunasosireintara is not null
        defaultStocShouldBeFound("lunasosireintara.specified=true");

        // Get all the stocList where lunasosireintara is null
        defaultStocShouldNotBeFound("lunasosireintara.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByLunasosireintaraContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where lunasosireintara contains DEFAULT_LUNASOSIREINTARA
        defaultStocShouldBeFound("lunasosireintara.contains=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the stocList where lunasosireintara contains UPDATED_LUNASOSIREINTARA
        defaultStocShouldNotBeFound("lunasosireintara.contains=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllStocsByLunasosireintaraNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where lunasosireintara does not contain DEFAULT_LUNASOSIREINTARA
        defaultStocShouldNotBeFound("lunasosireintara.doesNotContain=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the stocList where lunasosireintara does not contain UPDATED_LUNASOSIREINTARA
        defaultStocShouldBeFound("lunasosireintara.doesNotContain=" + UPDATED_LUNASOSIREINTARA);
    }


    @Test
    @Transactional
    public void getAllStocsByRezervataIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where rezervata equals to DEFAULT_REZERVATA
        defaultStocShouldBeFound("rezervata.equals=" + DEFAULT_REZERVATA);

        // Get all the stocList where rezervata equals to UPDATED_REZERVATA
        defaultStocShouldNotBeFound("rezervata.equals=" + UPDATED_REZERVATA);
    }

    @Test
    @Transactional
    public void getAllStocsByRezervataIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where rezervata not equals to DEFAULT_REZERVATA
        defaultStocShouldNotBeFound("rezervata.notEquals=" + DEFAULT_REZERVATA);

        // Get all the stocList where rezervata not equals to UPDATED_REZERVATA
        defaultStocShouldBeFound("rezervata.notEquals=" + UPDATED_REZERVATA);
    }

    @Test
    @Transactional
    public void getAllStocsByRezervataIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where rezervata in DEFAULT_REZERVATA or UPDATED_REZERVATA
        defaultStocShouldBeFound("rezervata.in=" + DEFAULT_REZERVATA + "," + UPDATED_REZERVATA);

        // Get all the stocList where rezervata equals to UPDATED_REZERVATA
        defaultStocShouldNotBeFound("rezervata.in=" + UPDATED_REZERVATA);
    }

    @Test
    @Transactional
    public void getAllStocsByRezervataIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where rezervata is not null
        defaultStocShouldBeFound("rezervata.specified=true");

        // Get all the stocList where rezervata is null
        defaultStocShouldNotBeFound("rezervata.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByRezervataContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where rezervata contains DEFAULT_REZERVATA
        defaultStocShouldBeFound("rezervata.contains=" + DEFAULT_REZERVATA);

        // Get all the stocList where rezervata contains UPDATED_REZERVATA
        defaultStocShouldNotBeFound("rezervata.contains=" + UPDATED_REZERVATA);
    }

    @Test
    @Transactional
    public void getAllStocsByRezervataNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where rezervata does not contain DEFAULT_REZERVATA
        defaultStocShouldNotBeFound("rezervata.doesNotContain=" + DEFAULT_REZERVATA);

        // Get all the stocList where rezervata does not contain UPDATED_REZERVATA
        defaultStocShouldBeFound("rezervata.doesNotContain=" + UPDATED_REZERVATA);
    }


    @Test
    @Transactional
    public void getAllStocsByDataexpirarerezIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where dataexpirarerez equals to DEFAULT_DATAEXPIRAREREZ
        defaultStocShouldBeFound("dataexpirarerez.equals=" + DEFAULT_DATAEXPIRAREREZ);

        // Get all the stocList where dataexpirarerez equals to UPDATED_DATAEXPIRAREREZ
        defaultStocShouldNotBeFound("dataexpirarerez.equals=" + UPDATED_DATAEXPIRAREREZ);
    }

    @Test
    @Transactional
    public void getAllStocsByDataexpirarerezIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where dataexpirarerez not equals to DEFAULT_DATAEXPIRAREREZ
        defaultStocShouldNotBeFound("dataexpirarerez.notEquals=" + DEFAULT_DATAEXPIRAREREZ);

        // Get all the stocList where dataexpirarerez not equals to UPDATED_DATAEXPIRAREREZ
        defaultStocShouldBeFound("dataexpirarerez.notEquals=" + UPDATED_DATAEXPIRAREREZ);
    }

    @Test
    @Transactional
    public void getAllStocsByDataexpirarerezIsInShouldWork() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where dataexpirarerez in DEFAULT_DATAEXPIRAREREZ or UPDATED_DATAEXPIRAREREZ
        defaultStocShouldBeFound("dataexpirarerez.in=" + DEFAULT_DATAEXPIRAREREZ + "," + UPDATED_DATAEXPIRAREREZ);

        // Get all the stocList where dataexpirarerez equals to UPDATED_DATAEXPIRAREREZ
        defaultStocShouldNotBeFound("dataexpirarerez.in=" + UPDATED_DATAEXPIRAREREZ);
    }

    @Test
    @Transactional
    public void getAllStocsByDataexpirarerezIsNullOrNotNull() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where dataexpirarerez is not null
        defaultStocShouldBeFound("dataexpirarerez.specified=true");

        // Get all the stocList where dataexpirarerez is null
        defaultStocShouldNotBeFound("dataexpirarerez.specified=false");
    }
                @Test
    @Transactional
    public void getAllStocsByDataexpirarerezContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where dataexpirarerez contains DEFAULT_DATAEXPIRAREREZ
        defaultStocShouldBeFound("dataexpirarerez.contains=" + DEFAULT_DATAEXPIRAREREZ);

        // Get all the stocList where dataexpirarerez contains UPDATED_DATAEXPIRAREREZ
        defaultStocShouldNotBeFound("dataexpirarerez.contains=" + UPDATED_DATAEXPIRAREREZ);
    }

    @Test
    @Transactional
    public void getAllStocsByDataexpirarerezNotContainsSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);

        // Get all the stocList where dataexpirarerez does not contain DEFAULT_DATAEXPIRAREREZ
        defaultStocShouldNotBeFound("dataexpirarerez.doesNotContain=" + DEFAULT_DATAEXPIRAREREZ);

        // Get all the stocList where dataexpirarerez does not contain UPDATED_DATAEXPIRAREREZ
        defaultStocShouldBeFound("dataexpirarerez.doesNotContain=" + UPDATED_DATAEXPIRAREREZ);
    }


    @Test
    @Transactional
    public void getAllStocsByDealerIsEqualToSomething() throws Exception {
        // Initialize the database
        stocRepository.saveAndFlush(stoc);
        Dealer dealer = DealerResourceIT.createEntity(em);
        em.persist(dealer);
        em.flush();
        stoc.addDealer(dealer);
        stocRepository.saveAndFlush(stoc);
        Long dealerId = dealer.getId();

        // Get all the stocList where dealer equals to dealerId
        defaultStocShouldBeFound("dealerId.equals=" + dealerId);

        // Get all the stocList where dealer equals to dealerId + 1
        defaultStocShouldNotBeFound("dealerId.equals=" + (dealerId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultStocShouldBeFound(String filter) throws Exception {
        restStocMockMvc.perform(get("/api/stocs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].htrocarno").value(hasItem(DEFAULT_HTROCARNO)))
            .andExpect(jsonPath("$.[*].resdealerid").value(hasItem(DEFAULT_RESDEALERID)))
            .andExpect(jsonPath("$.[*].anfabricatieciv").value(hasItem(DEFAULT_ANFABRICATIECIV)))
            .andExpect(jsonPath("$.[*].tipautovehicul").value(hasItem(DEFAULT_TIPAUTOVEHICUL)))
            .andExpect(jsonPath("$.[*].codculoareexterior").value(hasItem(DEFAULT_CODCULOAREEXTERIOR)))
            .andExpect(jsonPath("$.[*].descculoareexterior").value(hasItem(DEFAULT_DESCCULOAREEXTERIOR)))
            .andExpect(jsonPath("$.[*].vopseametalizata").value(hasItem(DEFAULT_VOPSEAMETALIZATA)))
            .andExpect(jsonPath("$.[*].culoareinterior").value(hasItem(DEFAULT_CULOAREINTERIOR)))
            .andExpect(jsonPath("$.[*].observatii").value(hasItem(DEFAULT_OBSERVATII)))
            .andExpect(jsonPath("$.[*].locatie").value(hasItem(DEFAULT_LOCATIE)))
            .andExpect(jsonPath("$.[*].omologareind").value(hasItem(DEFAULT_OMOLOGAREIND)))
            .andExpect(jsonPath("$.[*].lunasosireintara").value(hasItem(DEFAULT_LUNASOSIREINTARA)))
            .andExpect(jsonPath("$.[*].rezervata").value(hasItem(DEFAULT_REZERVATA)))
            .andExpect(jsonPath("$.[*].dataexpirarerez").value(hasItem(DEFAULT_DATAEXPIRAREREZ)));

        // Check, that the count call also returns 1
        restStocMockMvc.perform(get("/api/stocs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultStocShouldNotBeFound(String filter) throws Exception {
        restStocMockMvc.perform(get("/api/stocs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restStocMockMvc.perform(get("/api/stocs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingStoc() throws Exception {
        // Get the stoc
        restStocMockMvc.perform(get("/api/stocs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}

package com.bbc.myapp.web.rest;

import com.bbc.myapp.JavaHipsterApp;
import com.bbc.myapp.domain.Portofoliu;
import com.bbc.myapp.domain.Dealer;
import com.bbc.myapp.repository.PortofoliuRepository;
import com.bbc.myapp.service.PortofoliuService;
import com.bbc.myapp.service.dto.PortofoliuDTO;
import com.bbc.myapp.service.mapper.PortofoliuMapper;
import com.bbc.myapp.web.rest.errors.ExceptionTranslator;
import com.bbc.myapp.service.dto.PortofoliuCriteria;
import com.bbc.myapp.service.PortofoliuQueryService;

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
 * Integration tests for the {@link PortofoliuResource} REST controller.
 */
@SpringBootTest(classes = JavaHipsterApp.class)
public class PortofoliuResourceIT {

    private static final Integer DEFAULT_HTROCARNO = 1;
    private static final Integer UPDATED_HTROCARNO = 2;
    private static final Integer SMALLER_HTROCARNO = 1 - 1;

    private static final String DEFAULT_DEALER = "AAAAAAAAAA";
    private static final String UPDATED_DEALER = "BBBBBBBBBB";

    private static final String DEFAULT_DATAREZSAUFACTURA = "AAAAAAAAAA";
    private static final String UPDATED_DATAREZSAUFACTURA = "BBBBBBBBBB";

    private static final String DEFAULT_DATAEXPIRARE = "AAAAAAAAAA";
    private static final String UPDATED_DATAEXPIRARE = "BBBBBBBBBB";

    private static final Integer DEFAULT_RESDEALERID = 1;
    private static final Integer UPDATED_RESDEALERID = 2;
    private static final Integer SMALLER_RESDEALERID = 1 - 1;

    private static final String DEFAULT_TIPLINIE = "AAAAAAAAAA";
    private static final String UPDATED_TIPLINIE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATIE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIE = "BBBBBBBBBB";

    private static final String DEFAULT_LUNAPRODUCTIE = "AAAAAAAAAA";
    private static final String UPDATED_LUNAPRODUCTIE = "BBBBBBBBBB";

    private static final String DEFAULT_LUNASOSIREINTARA = "AAAAAAAAAA";
    private static final String UPDATED_LUNASOSIREINTARA = "BBBBBBBBBB";

    private static final String DEFAULT_CODMODEL = "AAAAAAAAAA";
    private static final String UPDATED_CODMODEL = "BBBBBBBBBB";

    private static final String DEFAULT_TIPAUTOVEHICUL = "AAAAAAAAAA";
    private static final String UPDATED_TIPAUTOVEHICUL = "BBBBBBBBBB";

    private static final String DEFAULT_CODCULOAREEXT = "AAAAAAAAAA";
    private static final String UPDATED_CODCULOAREEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CULOAREEXTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_CULOAREEXTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_CULOARE_INTEGERERIOR = "AAAAAAAAAA";
    private static final String UPDATED_CULOARE_INTEGERERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATII = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATII = "BBBBBBBBBB";

    private static final String DEFAULT_NUMECLIENT = "AAAAAAAAAA";
    private static final String UPDATED_NUMECLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEVANZATOR = "AAAAAAAAAA";
    private static final String UPDATED_NUMEVANZATOR = "BBBBBBBBBB";

    private static final String DEFAULT_VIN = "AAAAAAAAAA";
    private static final String UPDATED_VIN = "BBBBBBBBBB";

    private static final String DEFAULT_ENGINENO = "AAAAAAAAAA";
    private static final String UPDATED_ENGINENO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANFABRICATIECFCIV = 1;
    private static final Integer UPDATED_ANFABRICATIECFCIV = 2;
    private static final Integer SMALLER_ANFABRICATIECFCIV = 1 - 1;

    private static final String DEFAULT_OMOLOGAREINDIVIDUALA = "AAAAAAAAAA";
    private static final String UPDATED_OMOLOGAREINDIVIDUALA = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRETLISTA = 1;
    private static final Integer UPDATED_PRETLISTA = 2;
    private static final Integer SMALLER_PRETLISTA = 1 - 1;

    private static final Integer DEFAULT_DISCOUNTSTANDARD = 1;
    private static final Integer UPDATED_DISCOUNTSTANDARD = 2;
    private static final Integer SMALLER_DISCOUNTSTANDARD = 1 - 1;

    private static final Integer DEFAULT_DISCOUNTSUPLIMENTAR = 1;
    private static final Integer UPDATED_DISCOUNTSUPLIMENTAR = 2;
    private static final Integer SMALLER_DISCOUNTSUPLIMENTAR = 1 - 1;

    private static final Integer DEFAULT_TRUSALEGISLATIVA = 1;
    private static final Integer UPDATED_TRUSALEGISLATIVA = 2;
    private static final Integer SMALLER_TRUSALEGISLATIVA = 1 - 1;

    private static final Integer DEFAULT_PRETFINAL = 1;
    private static final Integer UPDATED_PRETFINAL = 2;
    private static final Integer SMALLER_PRETFINAL = 1 - 1;

    private static final Integer DEFAULT_AVANSPLATIT = 1;
    private static final Integer UPDATED_AVANSPLATIT = 2;
    private static final Integer SMALLER_AVANSPLATIT = 1 - 1;

    private static final Integer DEFAULT_RESTDEPLATA = 1;
    private static final Integer UPDATED_RESTDEPLATA = 2;
    private static final Integer SMALLER_RESTDEPLATA = 1 - 1;

    private static final Integer DEFAULT_CUSTOMERTRXID = 1;
    private static final Integer UPDATED_CUSTOMERTRXID = 2;
    private static final Integer SMALLER_CUSTOMERTRXID = 1 - 1;

    private static final String DEFAULT_REZCUSTID = "AAAAAAAAAA";
    private static final String UPDATED_REZCUSTID = "BBBBBBBBBB";

    private static final Integer DEFAULT_SOLDCUSTID = 1;
    private static final Integer UPDATED_SOLDCUSTID = 2;
    private static final Integer SMALLER_SOLDCUSTID = 1 - 1;

    private static final Boolean DEFAULT_PROFORMA = false;
    private static final Boolean UPDATED_PROFORMA = true;

    private static final Boolean DEFAULT_TRANSPORT = false;
    private static final Boolean UPDATED_TRANSPORT = true;

    @Autowired
    private PortofoliuRepository portofoliuRepository;

    @Autowired
    private PortofoliuMapper portofoliuMapper;

    @Autowired
    private PortofoliuService portofoliuService;

    @Autowired
    private PortofoliuQueryService portofoliuQueryService;

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

    private MockMvc restPortofoliuMockMvc;

    private Portofoliu portofoliu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PortofoliuResource portofoliuResource = new PortofoliuResource(portofoliuService, portofoliuQueryService);
        this.restPortofoliuMockMvc = MockMvcBuilders.standaloneSetup(portofoliuResource)
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
    public static Portofoliu createEntity(EntityManager em) {
        Portofoliu portofoliu = new Portofoliu()
            .htrocarno(DEFAULT_HTROCARNO)
            .dealer(DEFAULT_DEALER)
            .datarezsaufactura(DEFAULT_DATAREZSAUFACTURA)
            .dataexpirare(DEFAULT_DATAEXPIRARE)
            .resdealerid(DEFAULT_RESDEALERID)
            .tiplinie(DEFAULT_TIPLINIE)
            .locatie(DEFAULT_LOCATIE)
            .lunaproductie(DEFAULT_LUNAPRODUCTIE)
            .lunasosireintara(DEFAULT_LUNASOSIREINTARA)
            .codmodel(DEFAULT_CODMODEL)
            .tipautovehicul(DEFAULT_TIPAUTOVEHICUL)
            .codculoareext(DEFAULT_CODCULOAREEXT)
            .culoareexterior(DEFAULT_CULOAREEXTERIOR)
            .culoareIntegererior(DEFAULT_CULOARE_INTEGERERIOR)
            .observatii(DEFAULT_OBSERVATII)
            .numeclient(DEFAULT_NUMECLIENT)
            .numevanzator(DEFAULT_NUMEVANZATOR)
            .vin(DEFAULT_VIN)
            .engineno(DEFAULT_ENGINENO)
            .anfabricatiecfciv(DEFAULT_ANFABRICATIECFCIV)
            .omologareindividuala(DEFAULT_OMOLOGAREINDIVIDUALA)
            .pretlista(DEFAULT_PRETLISTA)
            .discountstandard(DEFAULT_DISCOUNTSTANDARD)
            .discountsuplimentar(DEFAULT_DISCOUNTSUPLIMENTAR)
            .trusalegislativa(DEFAULT_TRUSALEGISLATIVA)
            .pretfinal(DEFAULT_PRETFINAL)
            .avansplatit(DEFAULT_AVANSPLATIT)
            .restdeplata(DEFAULT_RESTDEPLATA)
            .customertrxid(DEFAULT_CUSTOMERTRXID)
            .rezcustid(DEFAULT_REZCUSTID)
            .soldcustid(DEFAULT_SOLDCUSTID)
            .proforma(DEFAULT_PROFORMA)
            .transport(DEFAULT_TRANSPORT);
        return portofoliu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Portofoliu createUpdatedEntity(EntityManager em) {
        Portofoliu portofoliu = new Portofoliu()
            .htrocarno(UPDATED_HTROCARNO)
            .dealer(UPDATED_DEALER)
            .datarezsaufactura(UPDATED_DATAREZSAUFACTURA)
            .dataexpirare(UPDATED_DATAEXPIRARE)
            .resdealerid(UPDATED_RESDEALERID)
            .tiplinie(UPDATED_TIPLINIE)
            .locatie(UPDATED_LOCATIE)
            .lunaproductie(UPDATED_LUNAPRODUCTIE)
            .lunasosireintara(UPDATED_LUNASOSIREINTARA)
            .codmodel(UPDATED_CODMODEL)
            .tipautovehicul(UPDATED_TIPAUTOVEHICUL)
            .codculoareext(UPDATED_CODCULOAREEXT)
            .culoareexterior(UPDATED_CULOAREEXTERIOR)
            .culoareIntegererior(UPDATED_CULOARE_INTEGERERIOR)
            .observatii(UPDATED_OBSERVATII)
            .numeclient(UPDATED_NUMECLIENT)
            .numevanzator(UPDATED_NUMEVANZATOR)
            .vin(UPDATED_VIN)
            .engineno(UPDATED_ENGINENO)
            .anfabricatiecfciv(UPDATED_ANFABRICATIECFCIV)
            .omologareindividuala(UPDATED_OMOLOGAREINDIVIDUALA)
            .pretlista(UPDATED_PRETLISTA)
            .discountstandard(UPDATED_DISCOUNTSTANDARD)
            .discountsuplimentar(UPDATED_DISCOUNTSUPLIMENTAR)
            .trusalegislativa(UPDATED_TRUSALEGISLATIVA)
            .pretfinal(UPDATED_PRETFINAL)
            .avansplatit(UPDATED_AVANSPLATIT)
            .restdeplata(UPDATED_RESTDEPLATA)
            .customertrxid(UPDATED_CUSTOMERTRXID)
            .rezcustid(UPDATED_REZCUSTID)
            .soldcustid(UPDATED_SOLDCUSTID)
            .proforma(UPDATED_PROFORMA)
            .transport(UPDATED_TRANSPORT);
        return portofoliu;
    }

    @BeforeEach
    public void initTest() {
        portofoliu = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllPortofolius() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList
        restPortofoliuMockMvc.perform(get("/api/portofolius?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(portofoliu.getId().intValue())))
            .andExpect(jsonPath("$.[*].htrocarno").value(hasItem(DEFAULT_HTROCARNO)))
            .andExpect(jsonPath("$.[*].dealer").value(hasItem(DEFAULT_DEALER)))
            .andExpect(jsonPath("$.[*].datarezsaufactura").value(hasItem(DEFAULT_DATAREZSAUFACTURA)))
            .andExpect(jsonPath("$.[*].dataexpirare").value(hasItem(DEFAULT_DATAEXPIRARE)))
            .andExpect(jsonPath("$.[*].resdealerid").value(hasItem(DEFAULT_RESDEALERID)))
            .andExpect(jsonPath("$.[*].tiplinie").value(hasItem(DEFAULT_TIPLINIE)))
            .andExpect(jsonPath("$.[*].locatie").value(hasItem(DEFAULT_LOCATIE)))
            .andExpect(jsonPath("$.[*].lunaproductie").value(hasItem(DEFAULT_LUNAPRODUCTIE)))
            .andExpect(jsonPath("$.[*].lunasosireintara").value(hasItem(DEFAULT_LUNASOSIREINTARA)))
            .andExpect(jsonPath("$.[*].codmodel").value(hasItem(DEFAULT_CODMODEL)))
            .andExpect(jsonPath("$.[*].tipautovehicul").value(hasItem(DEFAULT_TIPAUTOVEHICUL)))
            .andExpect(jsonPath("$.[*].codculoareext").value(hasItem(DEFAULT_CODCULOAREEXT)))
            .andExpect(jsonPath("$.[*].culoareexterior").value(hasItem(DEFAULT_CULOAREEXTERIOR)))
            .andExpect(jsonPath("$.[*].culoareIntegererior").value(hasItem(DEFAULT_CULOARE_INTEGERERIOR)))
            .andExpect(jsonPath("$.[*].observatii").value(hasItem(DEFAULT_OBSERVATII)))
            .andExpect(jsonPath("$.[*].numeclient").value(hasItem(DEFAULT_NUMECLIENT)))
            .andExpect(jsonPath("$.[*].numevanzator").value(hasItem(DEFAULT_NUMEVANZATOR)))
            .andExpect(jsonPath("$.[*].vin").value(hasItem(DEFAULT_VIN)))
            .andExpect(jsonPath("$.[*].engineno").value(hasItem(DEFAULT_ENGINENO)))
            .andExpect(jsonPath("$.[*].anfabricatiecfciv").value(hasItem(DEFAULT_ANFABRICATIECFCIV)))
            .andExpect(jsonPath("$.[*].omologareindividuala").value(hasItem(DEFAULT_OMOLOGAREINDIVIDUALA)))
            .andExpect(jsonPath("$.[*].pretlista").value(hasItem(DEFAULT_PRETLISTA)))
            .andExpect(jsonPath("$.[*].discountstandard").value(hasItem(DEFAULT_DISCOUNTSTANDARD)))
            .andExpect(jsonPath("$.[*].discountsuplimentar").value(hasItem(DEFAULT_DISCOUNTSUPLIMENTAR)))
            .andExpect(jsonPath("$.[*].trusalegislativa").value(hasItem(DEFAULT_TRUSALEGISLATIVA)))
            .andExpect(jsonPath("$.[*].pretfinal").value(hasItem(DEFAULT_PRETFINAL)))
            .andExpect(jsonPath("$.[*].avansplatit").value(hasItem(DEFAULT_AVANSPLATIT)))
            .andExpect(jsonPath("$.[*].restdeplata").value(hasItem(DEFAULT_RESTDEPLATA)))
            .andExpect(jsonPath("$.[*].customertrxid").value(hasItem(DEFAULT_CUSTOMERTRXID)))
            .andExpect(jsonPath("$.[*].rezcustid").value(hasItem(DEFAULT_REZCUSTID)))
            .andExpect(jsonPath("$.[*].soldcustid").value(hasItem(DEFAULT_SOLDCUSTID)))
            .andExpect(jsonPath("$.[*].proforma").value(hasItem(DEFAULT_PROFORMA.booleanValue())))
            .andExpect(jsonPath("$.[*].transport").value(hasItem(DEFAULT_TRANSPORT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPortofoliu() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get the portofoliu
        restPortofoliuMockMvc.perform(get("/api/portofolius/{id}", portofoliu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(portofoliu.getId().intValue()))
            .andExpect(jsonPath("$.htrocarno").value(DEFAULT_HTROCARNO))
            .andExpect(jsonPath("$.dealer").value(DEFAULT_DEALER))
            .andExpect(jsonPath("$.datarezsaufactura").value(DEFAULT_DATAREZSAUFACTURA))
            .andExpect(jsonPath("$.dataexpirare").value(DEFAULT_DATAEXPIRARE))
            .andExpect(jsonPath("$.resdealerid").value(DEFAULT_RESDEALERID))
            .andExpect(jsonPath("$.tiplinie").value(DEFAULT_TIPLINIE))
            .andExpect(jsonPath("$.locatie").value(DEFAULT_LOCATIE))
            .andExpect(jsonPath("$.lunaproductie").value(DEFAULT_LUNAPRODUCTIE))
            .andExpect(jsonPath("$.lunasosireintara").value(DEFAULT_LUNASOSIREINTARA))
            .andExpect(jsonPath("$.codmodel").value(DEFAULT_CODMODEL))
            .andExpect(jsonPath("$.tipautovehicul").value(DEFAULT_TIPAUTOVEHICUL))
            .andExpect(jsonPath("$.codculoareext").value(DEFAULT_CODCULOAREEXT))
            .andExpect(jsonPath("$.culoareexterior").value(DEFAULT_CULOAREEXTERIOR))
            .andExpect(jsonPath("$.culoareIntegererior").value(DEFAULT_CULOARE_INTEGERERIOR))
            .andExpect(jsonPath("$.observatii").value(DEFAULT_OBSERVATII))
            .andExpect(jsonPath("$.numeclient").value(DEFAULT_NUMECLIENT))
            .andExpect(jsonPath("$.numevanzator").value(DEFAULT_NUMEVANZATOR))
            .andExpect(jsonPath("$.vin").value(DEFAULT_VIN))
            .andExpect(jsonPath("$.engineno").value(DEFAULT_ENGINENO))
            .andExpect(jsonPath("$.anfabricatiecfciv").value(DEFAULT_ANFABRICATIECFCIV))
            .andExpect(jsonPath("$.omologareindividuala").value(DEFAULT_OMOLOGAREINDIVIDUALA))
            .andExpect(jsonPath("$.pretlista").value(DEFAULT_PRETLISTA))
            .andExpect(jsonPath("$.discountstandard").value(DEFAULT_DISCOUNTSTANDARD))
            .andExpect(jsonPath("$.discountsuplimentar").value(DEFAULT_DISCOUNTSUPLIMENTAR))
            .andExpect(jsonPath("$.trusalegislativa").value(DEFAULT_TRUSALEGISLATIVA))
            .andExpect(jsonPath("$.pretfinal").value(DEFAULT_PRETFINAL))
            .andExpect(jsonPath("$.avansplatit").value(DEFAULT_AVANSPLATIT))
            .andExpect(jsonPath("$.restdeplata").value(DEFAULT_RESTDEPLATA))
            .andExpect(jsonPath("$.customertrxid").value(DEFAULT_CUSTOMERTRXID))
            .andExpect(jsonPath("$.rezcustid").value(DEFAULT_REZCUSTID))
            .andExpect(jsonPath("$.soldcustid").value(DEFAULT_SOLDCUSTID))
            .andExpect(jsonPath("$.proforma").value(DEFAULT_PROFORMA.booleanValue()))
            .andExpect(jsonPath("$.transport").value(DEFAULT_TRANSPORT.booleanValue()));
    }


    @Test
    @Transactional
    public void getPortofoliusByIdFiltering() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        Long id = portofoliu.getId();

        defaultPortofoliuShouldBeFound("id.equals=" + id);
        defaultPortofoliuShouldNotBeFound("id.notEquals=" + id);

        defaultPortofoliuShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPortofoliuShouldNotBeFound("id.greaterThan=" + id);

        defaultPortofoliuShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPortofoliuShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno equals to DEFAULT_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.equals=" + DEFAULT_HTROCARNO);

        // Get all the portofoliuList where htrocarno equals to UPDATED_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.equals=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno not equals to DEFAULT_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.notEquals=" + DEFAULT_HTROCARNO);

        // Get all the portofoliuList where htrocarno not equals to UPDATED_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.notEquals=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno in DEFAULT_HTROCARNO or UPDATED_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.in=" + DEFAULT_HTROCARNO + "," + UPDATED_HTROCARNO);

        // Get all the portofoliuList where htrocarno equals to UPDATED_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.in=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno is not null
        defaultPortofoliuShouldBeFound("htrocarno.specified=true");

        // Get all the portofoliuList where htrocarno is null
        defaultPortofoliuShouldNotBeFound("htrocarno.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno is greater than or equal to DEFAULT_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.greaterThanOrEqual=" + DEFAULT_HTROCARNO);

        // Get all the portofoliuList where htrocarno is greater than or equal to UPDATED_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.greaterThanOrEqual=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno is less than or equal to DEFAULT_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.lessThanOrEqual=" + DEFAULT_HTROCARNO);

        // Get all the portofoliuList where htrocarno is less than or equal to SMALLER_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.lessThanOrEqual=" + SMALLER_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno is less than DEFAULT_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.lessThan=" + DEFAULT_HTROCARNO);

        // Get all the portofoliuList where htrocarno is less than UPDATED_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.lessThan=" + UPDATED_HTROCARNO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByHtrocarnoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where htrocarno is greater than DEFAULT_HTROCARNO
        defaultPortofoliuShouldNotBeFound("htrocarno.greaterThan=" + DEFAULT_HTROCARNO);

        // Get all the portofoliuList where htrocarno is greater than SMALLER_HTROCARNO
        defaultPortofoliuShouldBeFound("htrocarno.greaterThan=" + SMALLER_HTROCARNO);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByDealerIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dealer not equals to DEFAULT_DEALER
        defaultPortofoliuShouldNotBeFound("dealer.notEquals=" + DEFAULT_DEALER);

        // Get all the portofoliuList where dealer not equals to UPDATED_DEALER
        defaultPortofoliuShouldBeFound("dealer.notEquals=" + UPDATED_DEALER);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDealerIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dealer in DEFAULT_DEALER or UPDATED_DEALER
        defaultPortofoliuShouldBeFound("dealer.in=" + DEFAULT_DEALER + "," + UPDATED_DEALER);

        // Get all the portofoliuList where dealer equals to UPDATED_DEALER
        defaultPortofoliuShouldNotBeFound("dealer.in=" + UPDATED_DEALER);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDealerIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dealer is not null
        defaultPortofoliuShouldBeFound("dealer.specified=true");

        // Get all the portofoliuList where dealer is null
        defaultPortofoliuShouldNotBeFound("dealer.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByDealerContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dealer contains DEFAULT_DEALER
        defaultPortofoliuShouldBeFound("dealer.contains=" + DEFAULT_DEALER);

        // Get all the portofoliuList where dealer contains UPDATED_DEALER
        defaultPortofoliuShouldNotBeFound("dealer.contains=" + UPDATED_DEALER);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDealerNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dealer does not contain DEFAULT_DEALER
        defaultPortofoliuShouldNotBeFound("dealer.doesNotContain=" + DEFAULT_DEALER);

        // Get all the portofoliuList where dealer does not contain UPDATED_DEALER
        defaultPortofoliuShouldBeFound("dealer.doesNotContain=" + UPDATED_DEALER);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByDatarezsaufacturaIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where datarezsaufactura equals to DEFAULT_DATAREZSAUFACTURA
        defaultPortofoliuShouldBeFound("datarezsaufactura.equals=" + DEFAULT_DATAREZSAUFACTURA);

        // Get all the portofoliuList where datarezsaufactura equals to UPDATED_DATAREZSAUFACTURA
        defaultPortofoliuShouldNotBeFound("datarezsaufactura.equals=" + UPDATED_DATAREZSAUFACTURA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDatarezsaufacturaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where datarezsaufactura not equals to DEFAULT_DATAREZSAUFACTURA
        defaultPortofoliuShouldNotBeFound("datarezsaufactura.notEquals=" + DEFAULT_DATAREZSAUFACTURA);

        // Get all the portofoliuList where datarezsaufactura not equals to UPDATED_DATAREZSAUFACTURA
        defaultPortofoliuShouldBeFound("datarezsaufactura.notEquals=" + UPDATED_DATAREZSAUFACTURA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDatarezsaufacturaIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where datarezsaufactura in DEFAULT_DATAREZSAUFACTURA or UPDATED_DATAREZSAUFACTURA
        defaultPortofoliuShouldBeFound("datarezsaufactura.in=" + DEFAULT_DATAREZSAUFACTURA + "," + UPDATED_DATAREZSAUFACTURA);

        // Get all the portofoliuList where datarezsaufactura equals to UPDATED_DATAREZSAUFACTURA
        defaultPortofoliuShouldNotBeFound("datarezsaufactura.in=" + UPDATED_DATAREZSAUFACTURA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDatarezsaufacturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where datarezsaufactura is not null
        defaultPortofoliuShouldBeFound("datarezsaufactura.specified=true");

        // Get all the portofoliuList where datarezsaufactura is null
        defaultPortofoliuShouldNotBeFound("datarezsaufactura.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByDatarezsaufacturaContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where datarezsaufactura contains DEFAULT_DATAREZSAUFACTURA
        defaultPortofoliuShouldBeFound("datarezsaufactura.contains=" + DEFAULT_DATAREZSAUFACTURA);

        // Get all the portofoliuList where datarezsaufactura contains UPDATED_DATAREZSAUFACTURA
        defaultPortofoliuShouldNotBeFound("datarezsaufactura.contains=" + UPDATED_DATAREZSAUFACTURA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDatarezsaufacturaNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where datarezsaufactura does not contain DEFAULT_DATAREZSAUFACTURA
        defaultPortofoliuShouldNotBeFound("datarezsaufactura.doesNotContain=" + DEFAULT_DATAREZSAUFACTURA);

        // Get all the portofoliuList where datarezsaufactura does not contain UPDATED_DATAREZSAUFACTURA
        defaultPortofoliuShouldBeFound("datarezsaufactura.doesNotContain=" + UPDATED_DATAREZSAUFACTURA);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByDataexpirareIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dataexpirare equals to DEFAULT_DATAEXPIRARE
        defaultPortofoliuShouldBeFound("dataexpirare.equals=" + DEFAULT_DATAEXPIRARE);

        // Get all the portofoliuList where dataexpirare equals to UPDATED_DATAEXPIRARE
        defaultPortofoliuShouldNotBeFound("dataexpirare.equals=" + UPDATED_DATAEXPIRARE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDataexpirareIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dataexpirare not equals to DEFAULT_DATAEXPIRARE
        defaultPortofoliuShouldNotBeFound("dataexpirare.notEquals=" + DEFAULT_DATAEXPIRARE);

        // Get all the portofoliuList where dataexpirare not equals to UPDATED_DATAEXPIRARE
        defaultPortofoliuShouldBeFound("dataexpirare.notEquals=" + UPDATED_DATAEXPIRARE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDataexpirareIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dataexpirare in DEFAULT_DATAEXPIRARE or UPDATED_DATAEXPIRARE
        defaultPortofoliuShouldBeFound("dataexpirare.in=" + DEFAULT_DATAEXPIRARE + "," + UPDATED_DATAEXPIRARE);

        // Get all the portofoliuList where dataexpirare equals to UPDATED_DATAEXPIRARE
        defaultPortofoliuShouldNotBeFound("dataexpirare.in=" + UPDATED_DATAEXPIRARE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDataexpirareIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dataexpirare is not null
        defaultPortofoliuShouldBeFound("dataexpirare.specified=true");

        // Get all the portofoliuList where dataexpirare is null
        defaultPortofoliuShouldNotBeFound("dataexpirare.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByDataexpirareContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dataexpirare contains DEFAULT_DATAEXPIRARE
        defaultPortofoliuShouldBeFound("dataexpirare.contains=" + DEFAULT_DATAEXPIRARE);

        // Get all the portofoliuList where dataexpirare contains UPDATED_DATAEXPIRARE
        defaultPortofoliuShouldNotBeFound("dataexpirare.contains=" + UPDATED_DATAEXPIRARE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDataexpirareNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where dataexpirare does not contain DEFAULT_DATAEXPIRARE
        defaultPortofoliuShouldNotBeFound("dataexpirare.doesNotContain=" + DEFAULT_DATAEXPIRARE);

        // Get all the portofoliuList where dataexpirare does not contain UPDATED_DATAEXPIRARE
        defaultPortofoliuShouldBeFound("dataexpirare.doesNotContain=" + UPDATED_DATAEXPIRARE);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid equals to DEFAULT_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.equals=" + DEFAULT_RESDEALERID);

        // Get all the portofoliuList where resdealerid equals to UPDATED_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.equals=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid not equals to DEFAULT_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.notEquals=" + DEFAULT_RESDEALERID);

        // Get all the portofoliuList where resdealerid not equals to UPDATED_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.notEquals=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid in DEFAULT_RESDEALERID or UPDATED_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.in=" + DEFAULT_RESDEALERID + "," + UPDATED_RESDEALERID);

        // Get all the portofoliuList where resdealerid equals to UPDATED_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.in=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid is not null
        defaultPortofoliuShouldBeFound("resdealerid.specified=true");

        // Get all the portofoliuList where resdealerid is null
        defaultPortofoliuShouldNotBeFound("resdealerid.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid is greater than or equal to DEFAULT_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.greaterThanOrEqual=" + DEFAULT_RESDEALERID);

        // Get all the portofoliuList where resdealerid is greater than or equal to UPDATED_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.greaterThanOrEqual=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid is less than or equal to DEFAULT_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.lessThanOrEqual=" + DEFAULT_RESDEALERID);

        // Get all the portofoliuList where resdealerid is less than or equal to SMALLER_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.lessThanOrEqual=" + SMALLER_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid is less than DEFAULT_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.lessThan=" + DEFAULT_RESDEALERID);

        // Get all the portofoliuList where resdealerid is less than UPDATED_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.lessThan=" + UPDATED_RESDEALERID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByResdealeridIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where resdealerid is greater than DEFAULT_RESDEALERID
        defaultPortofoliuShouldNotBeFound("resdealerid.greaterThan=" + DEFAULT_RESDEALERID);

        // Get all the portofoliuList where resdealerid is greater than SMALLER_RESDEALERID
        defaultPortofoliuShouldBeFound("resdealerid.greaterThan=" + SMALLER_RESDEALERID);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByTiplinieIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tiplinie equals to DEFAULT_TIPLINIE
        defaultPortofoliuShouldBeFound("tiplinie.equals=" + DEFAULT_TIPLINIE);

        // Get all the portofoliuList where tiplinie equals to UPDATED_TIPLINIE
        defaultPortofoliuShouldNotBeFound("tiplinie.equals=" + UPDATED_TIPLINIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTiplinieIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tiplinie not equals to DEFAULT_TIPLINIE
        defaultPortofoliuShouldNotBeFound("tiplinie.notEquals=" + DEFAULT_TIPLINIE);

        // Get all the portofoliuList where tiplinie not equals to UPDATED_TIPLINIE
        defaultPortofoliuShouldBeFound("tiplinie.notEquals=" + UPDATED_TIPLINIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTiplinieIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tiplinie in DEFAULT_TIPLINIE or UPDATED_TIPLINIE
        defaultPortofoliuShouldBeFound("tiplinie.in=" + DEFAULT_TIPLINIE + "," + UPDATED_TIPLINIE);

        // Get all the portofoliuList where tiplinie equals to UPDATED_TIPLINIE
        defaultPortofoliuShouldNotBeFound("tiplinie.in=" + UPDATED_TIPLINIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTiplinieIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tiplinie is not null
        defaultPortofoliuShouldBeFound("tiplinie.specified=true");

        // Get all the portofoliuList where tiplinie is null
        defaultPortofoliuShouldNotBeFound("tiplinie.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByTiplinieContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tiplinie contains DEFAULT_TIPLINIE
        defaultPortofoliuShouldBeFound("tiplinie.contains=" + DEFAULT_TIPLINIE);

        // Get all the portofoliuList where tiplinie contains UPDATED_TIPLINIE
        defaultPortofoliuShouldNotBeFound("tiplinie.contains=" + UPDATED_TIPLINIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTiplinieNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tiplinie does not contain DEFAULT_TIPLINIE
        defaultPortofoliuShouldNotBeFound("tiplinie.doesNotContain=" + DEFAULT_TIPLINIE);

        // Get all the portofoliuList where tiplinie does not contain UPDATED_TIPLINIE
        defaultPortofoliuShouldBeFound("tiplinie.doesNotContain=" + UPDATED_TIPLINIE);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByLocatieIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where locatie equals to DEFAULT_LOCATIE
        defaultPortofoliuShouldBeFound("locatie.equals=" + DEFAULT_LOCATIE);

        // Get all the portofoliuList where locatie equals to UPDATED_LOCATIE
        defaultPortofoliuShouldNotBeFound("locatie.equals=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLocatieIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where locatie not equals to DEFAULT_LOCATIE
        defaultPortofoliuShouldNotBeFound("locatie.notEquals=" + DEFAULT_LOCATIE);

        // Get all the portofoliuList where locatie not equals to UPDATED_LOCATIE
        defaultPortofoliuShouldBeFound("locatie.notEquals=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLocatieIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where locatie in DEFAULT_LOCATIE or UPDATED_LOCATIE
        defaultPortofoliuShouldBeFound("locatie.in=" + DEFAULT_LOCATIE + "," + UPDATED_LOCATIE);

        // Get all the portofoliuList where locatie equals to UPDATED_LOCATIE
        defaultPortofoliuShouldNotBeFound("locatie.in=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLocatieIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where locatie is not null
        defaultPortofoliuShouldBeFound("locatie.specified=true");

        // Get all the portofoliuList where locatie is null
        defaultPortofoliuShouldNotBeFound("locatie.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByLocatieContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where locatie contains DEFAULT_LOCATIE
        defaultPortofoliuShouldBeFound("locatie.contains=" + DEFAULT_LOCATIE);

        // Get all the portofoliuList where locatie contains UPDATED_LOCATIE
        defaultPortofoliuShouldNotBeFound("locatie.contains=" + UPDATED_LOCATIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLocatieNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where locatie does not contain DEFAULT_LOCATIE
        defaultPortofoliuShouldNotBeFound("locatie.doesNotContain=" + DEFAULT_LOCATIE);

        // Get all the portofoliuList where locatie does not contain UPDATED_LOCATIE
        defaultPortofoliuShouldBeFound("locatie.doesNotContain=" + UPDATED_LOCATIE);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByLunaproductieIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunaproductie equals to DEFAULT_LUNAPRODUCTIE
        defaultPortofoliuShouldBeFound("lunaproductie.equals=" + DEFAULT_LUNAPRODUCTIE);

        // Get all the portofoliuList where lunaproductie equals to UPDATED_LUNAPRODUCTIE
        defaultPortofoliuShouldNotBeFound("lunaproductie.equals=" + UPDATED_LUNAPRODUCTIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunaproductieIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunaproductie not equals to DEFAULT_LUNAPRODUCTIE
        defaultPortofoliuShouldNotBeFound("lunaproductie.notEquals=" + DEFAULT_LUNAPRODUCTIE);

        // Get all the portofoliuList where lunaproductie not equals to UPDATED_LUNAPRODUCTIE
        defaultPortofoliuShouldBeFound("lunaproductie.notEquals=" + UPDATED_LUNAPRODUCTIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunaproductieIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunaproductie in DEFAULT_LUNAPRODUCTIE or UPDATED_LUNAPRODUCTIE
        defaultPortofoliuShouldBeFound("lunaproductie.in=" + DEFAULT_LUNAPRODUCTIE + "," + UPDATED_LUNAPRODUCTIE);

        // Get all the portofoliuList where lunaproductie equals to UPDATED_LUNAPRODUCTIE
        defaultPortofoliuShouldNotBeFound("lunaproductie.in=" + UPDATED_LUNAPRODUCTIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunaproductieIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunaproductie is not null
        defaultPortofoliuShouldBeFound("lunaproductie.specified=true");

        // Get all the portofoliuList where lunaproductie is null
        defaultPortofoliuShouldNotBeFound("lunaproductie.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByLunaproductieContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunaproductie contains DEFAULT_LUNAPRODUCTIE
        defaultPortofoliuShouldBeFound("lunaproductie.contains=" + DEFAULT_LUNAPRODUCTIE);

        // Get all the portofoliuList where lunaproductie contains UPDATED_LUNAPRODUCTIE
        defaultPortofoliuShouldNotBeFound("lunaproductie.contains=" + UPDATED_LUNAPRODUCTIE);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunaproductieNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunaproductie does not contain DEFAULT_LUNAPRODUCTIE
        defaultPortofoliuShouldNotBeFound("lunaproductie.doesNotContain=" + DEFAULT_LUNAPRODUCTIE);

        // Get all the portofoliuList where lunaproductie does not contain UPDATED_LUNAPRODUCTIE
        defaultPortofoliuShouldBeFound("lunaproductie.doesNotContain=" + UPDATED_LUNAPRODUCTIE);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByLunasosireintaraIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunasosireintara equals to DEFAULT_LUNASOSIREINTARA
        defaultPortofoliuShouldBeFound("lunasosireintara.equals=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the portofoliuList where lunasosireintara equals to UPDATED_LUNASOSIREINTARA
        defaultPortofoliuShouldNotBeFound("lunasosireintara.equals=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunasosireintaraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunasosireintara not equals to DEFAULT_LUNASOSIREINTARA
        defaultPortofoliuShouldNotBeFound("lunasosireintara.notEquals=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the portofoliuList where lunasosireintara not equals to UPDATED_LUNASOSIREINTARA
        defaultPortofoliuShouldBeFound("lunasosireintara.notEquals=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunasosireintaraIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunasosireintara in DEFAULT_LUNASOSIREINTARA or UPDATED_LUNASOSIREINTARA
        defaultPortofoliuShouldBeFound("lunasosireintara.in=" + DEFAULT_LUNASOSIREINTARA + "," + UPDATED_LUNASOSIREINTARA);

        // Get all the portofoliuList where lunasosireintara equals to UPDATED_LUNASOSIREINTARA
        defaultPortofoliuShouldNotBeFound("lunasosireintara.in=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunasosireintaraIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunasosireintara is not null
        defaultPortofoliuShouldBeFound("lunasosireintara.specified=true");

        // Get all the portofoliuList where lunasosireintara is null
        defaultPortofoliuShouldNotBeFound("lunasosireintara.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByLunasosireintaraContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunasosireintara contains DEFAULT_LUNASOSIREINTARA
        defaultPortofoliuShouldBeFound("lunasosireintara.contains=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the portofoliuList where lunasosireintara contains UPDATED_LUNASOSIREINTARA
        defaultPortofoliuShouldNotBeFound("lunasosireintara.contains=" + UPDATED_LUNASOSIREINTARA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByLunasosireintaraNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where lunasosireintara does not contain DEFAULT_LUNASOSIREINTARA
        defaultPortofoliuShouldNotBeFound("lunasosireintara.doesNotContain=" + DEFAULT_LUNASOSIREINTARA);

        // Get all the portofoliuList where lunasosireintara does not contain UPDATED_LUNASOSIREINTARA
        defaultPortofoliuShouldBeFound("lunasosireintara.doesNotContain=" + UPDATED_LUNASOSIREINTARA);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByCodmodelIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codmodel equals to DEFAULT_CODMODEL
        defaultPortofoliuShouldBeFound("codmodel.equals=" + DEFAULT_CODMODEL);

        // Get all the portofoliuList where codmodel equals to UPDATED_CODMODEL
        defaultPortofoliuShouldNotBeFound("codmodel.equals=" + UPDATED_CODMODEL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodmodelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codmodel not equals to DEFAULT_CODMODEL
        defaultPortofoliuShouldNotBeFound("codmodel.notEquals=" + DEFAULT_CODMODEL);

        // Get all the portofoliuList where codmodel not equals to UPDATED_CODMODEL
        defaultPortofoliuShouldBeFound("codmodel.notEquals=" + UPDATED_CODMODEL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodmodelIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codmodel in DEFAULT_CODMODEL or UPDATED_CODMODEL
        defaultPortofoliuShouldBeFound("codmodel.in=" + DEFAULT_CODMODEL + "," + UPDATED_CODMODEL);

        // Get all the portofoliuList where codmodel equals to UPDATED_CODMODEL
        defaultPortofoliuShouldNotBeFound("codmodel.in=" + UPDATED_CODMODEL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodmodelIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codmodel is not null
        defaultPortofoliuShouldBeFound("codmodel.specified=true");

        // Get all the portofoliuList where codmodel is null
        defaultPortofoliuShouldNotBeFound("codmodel.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByCodmodelContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codmodel contains DEFAULT_CODMODEL
        defaultPortofoliuShouldBeFound("codmodel.contains=" + DEFAULT_CODMODEL);

        // Get all the portofoliuList where codmodel contains UPDATED_CODMODEL
        defaultPortofoliuShouldNotBeFound("codmodel.contains=" + UPDATED_CODMODEL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodmodelNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codmodel does not contain DEFAULT_CODMODEL
        defaultPortofoliuShouldNotBeFound("codmodel.doesNotContain=" + DEFAULT_CODMODEL);

        // Get all the portofoliuList where codmodel does not contain UPDATED_CODMODEL
        defaultPortofoliuShouldBeFound("codmodel.doesNotContain=" + UPDATED_CODMODEL);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByTipautovehiculIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tipautovehicul equals to DEFAULT_TIPAUTOVEHICUL
        defaultPortofoliuShouldBeFound("tipautovehicul.equals=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the portofoliuList where tipautovehicul equals to UPDATED_TIPAUTOVEHICUL
        defaultPortofoliuShouldNotBeFound("tipautovehicul.equals=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTipautovehiculIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tipautovehicul not equals to DEFAULT_TIPAUTOVEHICUL
        defaultPortofoliuShouldNotBeFound("tipautovehicul.notEquals=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the portofoliuList where tipautovehicul not equals to UPDATED_TIPAUTOVEHICUL
        defaultPortofoliuShouldBeFound("tipautovehicul.notEquals=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTipautovehiculIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tipautovehicul in DEFAULT_TIPAUTOVEHICUL or UPDATED_TIPAUTOVEHICUL
        defaultPortofoliuShouldBeFound("tipautovehicul.in=" + DEFAULT_TIPAUTOVEHICUL + "," + UPDATED_TIPAUTOVEHICUL);

        // Get all the portofoliuList where tipautovehicul equals to UPDATED_TIPAUTOVEHICUL
        defaultPortofoliuShouldNotBeFound("tipautovehicul.in=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTipautovehiculIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tipautovehicul is not null
        defaultPortofoliuShouldBeFound("tipautovehicul.specified=true");

        // Get all the portofoliuList where tipautovehicul is null
        defaultPortofoliuShouldNotBeFound("tipautovehicul.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByTipautovehiculContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tipautovehicul contains DEFAULT_TIPAUTOVEHICUL
        defaultPortofoliuShouldBeFound("tipautovehicul.contains=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the portofoliuList where tipautovehicul contains UPDATED_TIPAUTOVEHICUL
        defaultPortofoliuShouldNotBeFound("tipautovehicul.contains=" + UPDATED_TIPAUTOVEHICUL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTipautovehiculNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where tipautovehicul does not contain DEFAULT_TIPAUTOVEHICUL
        defaultPortofoliuShouldNotBeFound("tipautovehicul.doesNotContain=" + DEFAULT_TIPAUTOVEHICUL);

        // Get all the portofoliuList where tipautovehicul does not contain UPDATED_TIPAUTOVEHICUL
        defaultPortofoliuShouldBeFound("tipautovehicul.doesNotContain=" + UPDATED_TIPAUTOVEHICUL);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByCodculoareextIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codculoareext equals to DEFAULT_CODCULOAREEXT
        defaultPortofoliuShouldBeFound("codculoareext.equals=" + DEFAULT_CODCULOAREEXT);

        // Get all the portofoliuList where codculoareext equals to UPDATED_CODCULOAREEXT
        defaultPortofoliuShouldNotBeFound("codculoareext.equals=" + UPDATED_CODCULOAREEXT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodculoareextIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codculoareext not equals to DEFAULT_CODCULOAREEXT
        defaultPortofoliuShouldNotBeFound("codculoareext.notEquals=" + DEFAULT_CODCULOAREEXT);

        // Get all the portofoliuList where codculoareext not equals to UPDATED_CODCULOAREEXT
        defaultPortofoliuShouldBeFound("codculoareext.notEquals=" + UPDATED_CODCULOAREEXT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodculoareextIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codculoareext in DEFAULT_CODCULOAREEXT or UPDATED_CODCULOAREEXT
        defaultPortofoliuShouldBeFound("codculoareext.in=" + DEFAULT_CODCULOAREEXT + "," + UPDATED_CODCULOAREEXT);

        // Get all the portofoliuList where codculoareext equals to UPDATED_CODCULOAREEXT
        defaultPortofoliuShouldNotBeFound("codculoareext.in=" + UPDATED_CODCULOAREEXT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodculoareextIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codculoareext is not null
        defaultPortofoliuShouldBeFound("codculoareext.specified=true");

        // Get all the portofoliuList where codculoareext is null
        defaultPortofoliuShouldNotBeFound("codculoareext.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByCodculoareextContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codculoareext contains DEFAULT_CODCULOAREEXT
        defaultPortofoliuShouldBeFound("codculoareext.contains=" + DEFAULT_CODCULOAREEXT);

        // Get all the portofoliuList where codculoareext contains UPDATED_CODCULOAREEXT
        defaultPortofoliuShouldNotBeFound("codculoareext.contains=" + UPDATED_CODCULOAREEXT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCodculoareextNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where codculoareext does not contain DEFAULT_CODCULOAREEXT
        defaultPortofoliuShouldNotBeFound("codculoareext.doesNotContain=" + DEFAULT_CODCULOAREEXT);

        // Get all the portofoliuList where codculoareext does not contain UPDATED_CODCULOAREEXT
        defaultPortofoliuShouldBeFound("codculoareext.doesNotContain=" + UPDATED_CODCULOAREEXT);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByCuloareexteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareexterior equals to DEFAULT_CULOAREEXTERIOR
        defaultPortofoliuShouldBeFound("culoareexterior.equals=" + DEFAULT_CULOAREEXTERIOR);

        // Get all the portofoliuList where culoareexterior equals to UPDATED_CULOAREEXTERIOR
        defaultPortofoliuShouldNotBeFound("culoareexterior.equals=" + UPDATED_CULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareexteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareexterior not equals to DEFAULT_CULOAREEXTERIOR
        defaultPortofoliuShouldNotBeFound("culoareexterior.notEquals=" + DEFAULT_CULOAREEXTERIOR);

        // Get all the portofoliuList where culoareexterior not equals to UPDATED_CULOAREEXTERIOR
        defaultPortofoliuShouldBeFound("culoareexterior.notEquals=" + UPDATED_CULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareexteriorIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareexterior in DEFAULT_CULOAREEXTERIOR or UPDATED_CULOAREEXTERIOR
        defaultPortofoliuShouldBeFound("culoareexterior.in=" + DEFAULT_CULOAREEXTERIOR + "," + UPDATED_CULOAREEXTERIOR);

        // Get all the portofoliuList where culoareexterior equals to UPDATED_CULOAREEXTERIOR
        defaultPortofoliuShouldNotBeFound("culoareexterior.in=" + UPDATED_CULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareexteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareexterior is not null
        defaultPortofoliuShouldBeFound("culoareexterior.specified=true");

        // Get all the portofoliuList where culoareexterior is null
        defaultPortofoliuShouldNotBeFound("culoareexterior.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByCuloareexteriorContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareexterior contains DEFAULT_CULOAREEXTERIOR
        defaultPortofoliuShouldBeFound("culoareexterior.contains=" + DEFAULT_CULOAREEXTERIOR);

        // Get all the portofoliuList where culoareexterior contains UPDATED_CULOAREEXTERIOR
        defaultPortofoliuShouldNotBeFound("culoareexterior.contains=" + UPDATED_CULOAREEXTERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareexteriorNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareexterior does not contain DEFAULT_CULOAREEXTERIOR
        defaultPortofoliuShouldNotBeFound("culoareexterior.doesNotContain=" + DEFAULT_CULOAREEXTERIOR);

        // Get all the portofoliuList where culoareexterior does not contain UPDATED_CULOAREEXTERIOR
        defaultPortofoliuShouldBeFound("culoareexterior.doesNotContain=" + UPDATED_CULOAREEXTERIOR);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByCuloareIntegereriorIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareIntegererior equals to DEFAULT_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldBeFound("culoareIntegererior.equals=" + DEFAULT_CULOARE_INTEGERERIOR);

        // Get all the portofoliuList where culoareIntegererior equals to UPDATED_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldNotBeFound("culoareIntegererior.equals=" + UPDATED_CULOARE_INTEGERERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareIntegereriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareIntegererior not equals to DEFAULT_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldNotBeFound("culoareIntegererior.notEquals=" + DEFAULT_CULOARE_INTEGERERIOR);

        // Get all the portofoliuList where culoareIntegererior not equals to UPDATED_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldBeFound("culoareIntegererior.notEquals=" + UPDATED_CULOARE_INTEGERERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareIntegereriorIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareIntegererior in DEFAULT_CULOARE_INTEGERERIOR or UPDATED_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldBeFound("culoareIntegererior.in=" + DEFAULT_CULOARE_INTEGERERIOR + "," + UPDATED_CULOARE_INTEGERERIOR);

        // Get all the portofoliuList where culoareIntegererior equals to UPDATED_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldNotBeFound("culoareIntegererior.in=" + UPDATED_CULOARE_INTEGERERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareIntegereriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareIntegererior is not null
        defaultPortofoliuShouldBeFound("culoareIntegererior.specified=true");

        // Get all the portofoliuList where culoareIntegererior is null
        defaultPortofoliuShouldNotBeFound("culoareIntegererior.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByCuloareIntegereriorContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareIntegererior contains DEFAULT_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldBeFound("culoareIntegererior.contains=" + DEFAULT_CULOARE_INTEGERERIOR);

        // Get all the portofoliuList where culoareIntegererior contains UPDATED_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldNotBeFound("culoareIntegererior.contains=" + UPDATED_CULOARE_INTEGERERIOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCuloareIntegereriorNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where culoareIntegererior does not contain DEFAULT_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldNotBeFound("culoareIntegererior.doesNotContain=" + DEFAULT_CULOARE_INTEGERERIOR);

        // Get all the portofoliuList where culoareIntegererior does not contain UPDATED_CULOARE_INTEGERERIOR
        defaultPortofoliuShouldBeFound("culoareIntegererior.doesNotContain=" + UPDATED_CULOARE_INTEGERERIOR);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByObservatiiIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where observatii equals to DEFAULT_OBSERVATII
        defaultPortofoliuShouldBeFound("observatii.equals=" + DEFAULT_OBSERVATII);

        // Get all the portofoliuList where observatii equals to UPDATED_OBSERVATII
        defaultPortofoliuShouldNotBeFound("observatii.equals=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByObservatiiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where observatii not equals to DEFAULT_OBSERVATII
        defaultPortofoliuShouldNotBeFound("observatii.notEquals=" + DEFAULT_OBSERVATII);

        // Get all the portofoliuList where observatii not equals to UPDATED_OBSERVATII
        defaultPortofoliuShouldBeFound("observatii.notEquals=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByObservatiiIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where observatii in DEFAULT_OBSERVATII or UPDATED_OBSERVATII
        defaultPortofoliuShouldBeFound("observatii.in=" + DEFAULT_OBSERVATII + "," + UPDATED_OBSERVATII);

        // Get all the portofoliuList where observatii equals to UPDATED_OBSERVATII
        defaultPortofoliuShouldNotBeFound("observatii.in=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByObservatiiIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where observatii is not null
        defaultPortofoliuShouldBeFound("observatii.specified=true");

        // Get all the portofoliuList where observatii is null
        defaultPortofoliuShouldNotBeFound("observatii.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByObservatiiContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where observatii contains DEFAULT_OBSERVATII
        defaultPortofoliuShouldBeFound("observatii.contains=" + DEFAULT_OBSERVATII);

        // Get all the portofoliuList where observatii contains UPDATED_OBSERVATII
        defaultPortofoliuShouldNotBeFound("observatii.contains=" + UPDATED_OBSERVATII);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByObservatiiNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where observatii does not contain DEFAULT_OBSERVATII
        defaultPortofoliuShouldNotBeFound("observatii.doesNotContain=" + DEFAULT_OBSERVATII);

        // Get all the portofoliuList where observatii does not contain UPDATED_OBSERVATII
        defaultPortofoliuShouldBeFound("observatii.doesNotContain=" + UPDATED_OBSERVATII);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByNumeclientIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numeclient equals to DEFAULT_NUMECLIENT
        defaultPortofoliuShouldBeFound("numeclient.equals=" + DEFAULT_NUMECLIENT);

        // Get all the portofoliuList where numeclient equals to UPDATED_NUMECLIENT
        defaultPortofoliuShouldNotBeFound("numeclient.equals=" + UPDATED_NUMECLIENT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumeclientIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numeclient not equals to DEFAULT_NUMECLIENT
        defaultPortofoliuShouldNotBeFound("numeclient.notEquals=" + DEFAULT_NUMECLIENT);

        // Get all the portofoliuList where numeclient not equals to UPDATED_NUMECLIENT
        defaultPortofoliuShouldBeFound("numeclient.notEquals=" + UPDATED_NUMECLIENT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumeclientIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numeclient in DEFAULT_NUMECLIENT or UPDATED_NUMECLIENT
        defaultPortofoliuShouldBeFound("numeclient.in=" + DEFAULT_NUMECLIENT + "," + UPDATED_NUMECLIENT);

        // Get all the portofoliuList where numeclient equals to UPDATED_NUMECLIENT
        defaultPortofoliuShouldNotBeFound("numeclient.in=" + UPDATED_NUMECLIENT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumeclientIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numeclient is not null
        defaultPortofoliuShouldBeFound("numeclient.specified=true");

        // Get all the portofoliuList where numeclient is null
        defaultPortofoliuShouldNotBeFound("numeclient.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByNumeclientContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numeclient contains DEFAULT_NUMECLIENT
        defaultPortofoliuShouldBeFound("numeclient.contains=" + DEFAULT_NUMECLIENT);

        // Get all the portofoliuList where numeclient contains UPDATED_NUMECLIENT
        defaultPortofoliuShouldNotBeFound("numeclient.contains=" + UPDATED_NUMECLIENT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumeclientNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numeclient does not contain DEFAULT_NUMECLIENT
        defaultPortofoliuShouldNotBeFound("numeclient.doesNotContain=" + DEFAULT_NUMECLIENT);

        // Get all the portofoliuList where numeclient does not contain UPDATED_NUMECLIENT
        defaultPortofoliuShouldBeFound("numeclient.doesNotContain=" + UPDATED_NUMECLIENT);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByNumevanzatorIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numevanzator equals to DEFAULT_NUMEVANZATOR
        defaultPortofoliuShouldBeFound("numevanzator.equals=" + DEFAULT_NUMEVANZATOR);

        // Get all the portofoliuList where numevanzator equals to UPDATED_NUMEVANZATOR
        defaultPortofoliuShouldNotBeFound("numevanzator.equals=" + UPDATED_NUMEVANZATOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumevanzatorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numevanzator not equals to DEFAULT_NUMEVANZATOR
        defaultPortofoliuShouldNotBeFound("numevanzator.notEquals=" + DEFAULT_NUMEVANZATOR);

        // Get all the portofoliuList where numevanzator not equals to UPDATED_NUMEVANZATOR
        defaultPortofoliuShouldBeFound("numevanzator.notEquals=" + UPDATED_NUMEVANZATOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumevanzatorIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numevanzator in DEFAULT_NUMEVANZATOR or UPDATED_NUMEVANZATOR
        defaultPortofoliuShouldBeFound("numevanzator.in=" + DEFAULT_NUMEVANZATOR + "," + UPDATED_NUMEVANZATOR);

        // Get all the portofoliuList where numevanzator equals to UPDATED_NUMEVANZATOR
        defaultPortofoliuShouldNotBeFound("numevanzator.in=" + UPDATED_NUMEVANZATOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumevanzatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numevanzator is not null
        defaultPortofoliuShouldBeFound("numevanzator.specified=true");

        // Get all the portofoliuList where numevanzator is null
        defaultPortofoliuShouldNotBeFound("numevanzator.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByNumevanzatorContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numevanzator contains DEFAULT_NUMEVANZATOR
        defaultPortofoliuShouldBeFound("numevanzator.contains=" + DEFAULT_NUMEVANZATOR);

        // Get all the portofoliuList where numevanzator contains UPDATED_NUMEVANZATOR
        defaultPortofoliuShouldNotBeFound("numevanzator.contains=" + UPDATED_NUMEVANZATOR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByNumevanzatorNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where numevanzator does not contain DEFAULT_NUMEVANZATOR
        defaultPortofoliuShouldNotBeFound("numevanzator.doesNotContain=" + DEFAULT_NUMEVANZATOR);

        // Get all the portofoliuList where numevanzator does not contain UPDATED_NUMEVANZATOR
        defaultPortofoliuShouldBeFound("numevanzator.doesNotContain=" + UPDATED_NUMEVANZATOR);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByVinIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where vin equals to DEFAULT_VIN
        defaultPortofoliuShouldBeFound("vin.equals=" + DEFAULT_VIN);

        // Get all the portofoliuList where vin equals to UPDATED_VIN
        defaultPortofoliuShouldNotBeFound("vin.equals=" + UPDATED_VIN);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByVinIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where vin not equals to DEFAULT_VIN
        defaultPortofoliuShouldNotBeFound("vin.notEquals=" + DEFAULT_VIN);

        // Get all the portofoliuList where vin not equals to UPDATED_VIN
        defaultPortofoliuShouldBeFound("vin.notEquals=" + UPDATED_VIN);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByVinIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where vin in DEFAULT_VIN or UPDATED_VIN
        defaultPortofoliuShouldBeFound("vin.in=" + DEFAULT_VIN + "," + UPDATED_VIN);

        // Get all the portofoliuList where vin equals to UPDATED_VIN
        defaultPortofoliuShouldNotBeFound("vin.in=" + UPDATED_VIN);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByVinIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where vin is not null
        defaultPortofoliuShouldBeFound("vin.specified=true");

        // Get all the portofoliuList where vin is null
        defaultPortofoliuShouldNotBeFound("vin.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByVinContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where vin contains DEFAULT_VIN
        defaultPortofoliuShouldBeFound("vin.contains=" + DEFAULT_VIN);

        // Get all the portofoliuList where vin contains UPDATED_VIN
        defaultPortofoliuShouldNotBeFound("vin.contains=" + UPDATED_VIN);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByVinNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where vin does not contain DEFAULT_VIN
        defaultPortofoliuShouldNotBeFound("vin.doesNotContain=" + DEFAULT_VIN);

        // Get all the portofoliuList where vin does not contain UPDATED_VIN
        defaultPortofoliuShouldBeFound("vin.doesNotContain=" + UPDATED_VIN);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByEnginenoIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where engineno equals to DEFAULT_ENGINENO
        defaultPortofoliuShouldBeFound("engineno.equals=" + DEFAULT_ENGINENO);

        // Get all the portofoliuList where engineno equals to UPDATED_ENGINENO
        defaultPortofoliuShouldNotBeFound("engineno.equals=" + UPDATED_ENGINENO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByEnginenoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where engineno not equals to DEFAULT_ENGINENO
        defaultPortofoliuShouldNotBeFound("engineno.notEquals=" + DEFAULT_ENGINENO);

        // Get all the portofoliuList where engineno not equals to UPDATED_ENGINENO
        defaultPortofoliuShouldBeFound("engineno.notEquals=" + UPDATED_ENGINENO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByEnginenoIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where engineno in DEFAULT_ENGINENO or UPDATED_ENGINENO
        defaultPortofoliuShouldBeFound("engineno.in=" + DEFAULT_ENGINENO + "," + UPDATED_ENGINENO);

        // Get all the portofoliuList where engineno equals to UPDATED_ENGINENO
        defaultPortofoliuShouldNotBeFound("engineno.in=" + UPDATED_ENGINENO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByEnginenoIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where engineno is not null
        defaultPortofoliuShouldBeFound("engineno.specified=true");

        // Get all the portofoliuList where engineno is null
        defaultPortofoliuShouldNotBeFound("engineno.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByEnginenoContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where engineno contains DEFAULT_ENGINENO
        defaultPortofoliuShouldBeFound("engineno.contains=" + DEFAULT_ENGINENO);

        // Get all the portofoliuList where engineno contains UPDATED_ENGINENO
        defaultPortofoliuShouldNotBeFound("engineno.contains=" + UPDATED_ENGINENO);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByEnginenoNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where engineno does not contain DEFAULT_ENGINENO
        defaultPortofoliuShouldNotBeFound("engineno.doesNotContain=" + DEFAULT_ENGINENO);

        // Get all the portofoliuList where engineno does not contain UPDATED_ENGINENO
        defaultPortofoliuShouldBeFound("engineno.doesNotContain=" + UPDATED_ENGINENO);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv equals to DEFAULT_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.equals=" + DEFAULT_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv equals to UPDATED_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.equals=" + UPDATED_ANFABRICATIECFCIV);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv not equals to DEFAULT_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.notEquals=" + DEFAULT_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv not equals to UPDATED_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.notEquals=" + UPDATED_ANFABRICATIECFCIV);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv in DEFAULT_ANFABRICATIECFCIV or UPDATED_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.in=" + DEFAULT_ANFABRICATIECFCIV + "," + UPDATED_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv equals to UPDATED_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.in=" + UPDATED_ANFABRICATIECFCIV);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv is not null
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.specified=true");

        // Get all the portofoliuList where anfabricatiecfciv is null
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv is greater than or equal to DEFAULT_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.greaterThanOrEqual=" + DEFAULT_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv is greater than or equal to UPDATED_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.greaterThanOrEqual=" + UPDATED_ANFABRICATIECFCIV);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv is less than or equal to DEFAULT_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.lessThanOrEqual=" + DEFAULT_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv is less than or equal to SMALLER_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.lessThanOrEqual=" + SMALLER_ANFABRICATIECFCIV);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv is less than DEFAULT_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.lessThan=" + DEFAULT_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv is less than UPDATED_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.lessThan=" + UPDATED_ANFABRICATIECFCIV);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAnfabricatiecfcivIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where anfabricatiecfciv is greater than DEFAULT_ANFABRICATIECFCIV
        defaultPortofoliuShouldNotBeFound("anfabricatiecfciv.greaterThan=" + DEFAULT_ANFABRICATIECFCIV);

        // Get all the portofoliuList where anfabricatiecfciv is greater than SMALLER_ANFABRICATIECFCIV
        defaultPortofoliuShouldBeFound("anfabricatiecfciv.greaterThan=" + SMALLER_ANFABRICATIECFCIV);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByOmologareindividualaIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where omologareindividuala equals to DEFAULT_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldBeFound("omologareindividuala.equals=" + DEFAULT_OMOLOGAREINDIVIDUALA);

        // Get all the portofoliuList where omologareindividuala equals to UPDATED_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldNotBeFound("omologareindividuala.equals=" + UPDATED_OMOLOGAREINDIVIDUALA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByOmologareindividualaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where omologareindividuala not equals to DEFAULT_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldNotBeFound("omologareindividuala.notEquals=" + DEFAULT_OMOLOGAREINDIVIDUALA);

        // Get all the portofoliuList where omologareindividuala not equals to UPDATED_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldBeFound("omologareindividuala.notEquals=" + UPDATED_OMOLOGAREINDIVIDUALA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByOmologareindividualaIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where omologareindividuala in DEFAULT_OMOLOGAREINDIVIDUALA or UPDATED_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldBeFound("omologareindividuala.in=" + DEFAULT_OMOLOGAREINDIVIDUALA + "," + UPDATED_OMOLOGAREINDIVIDUALA);

        // Get all the portofoliuList where omologareindividuala equals to UPDATED_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldNotBeFound("omologareindividuala.in=" + UPDATED_OMOLOGAREINDIVIDUALA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByOmologareindividualaIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where omologareindividuala is not null
        defaultPortofoliuShouldBeFound("omologareindividuala.specified=true");

        // Get all the portofoliuList where omologareindividuala is null
        defaultPortofoliuShouldNotBeFound("omologareindividuala.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByOmologareindividualaContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where omologareindividuala contains DEFAULT_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldBeFound("omologareindividuala.contains=" + DEFAULT_OMOLOGAREINDIVIDUALA);

        // Get all the portofoliuList where omologareindividuala contains UPDATED_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldNotBeFound("omologareindividuala.contains=" + UPDATED_OMOLOGAREINDIVIDUALA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByOmologareindividualaNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where omologareindividuala does not contain DEFAULT_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldNotBeFound("omologareindividuala.doesNotContain=" + DEFAULT_OMOLOGAREINDIVIDUALA);

        // Get all the portofoliuList where omologareindividuala does not contain UPDATED_OMOLOGAREINDIVIDUALA
        defaultPortofoliuShouldBeFound("omologareindividuala.doesNotContain=" + UPDATED_OMOLOGAREINDIVIDUALA);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista equals to DEFAULT_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.equals=" + DEFAULT_PRETLISTA);

        // Get all the portofoliuList where pretlista equals to UPDATED_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.equals=" + UPDATED_PRETLISTA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista not equals to DEFAULT_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.notEquals=" + DEFAULT_PRETLISTA);

        // Get all the portofoliuList where pretlista not equals to UPDATED_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.notEquals=" + UPDATED_PRETLISTA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista in DEFAULT_PRETLISTA or UPDATED_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.in=" + DEFAULT_PRETLISTA + "," + UPDATED_PRETLISTA);

        // Get all the portofoliuList where pretlista equals to UPDATED_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.in=" + UPDATED_PRETLISTA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista is not null
        defaultPortofoliuShouldBeFound("pretlista.specified=true");

        // Get all the portofoliuList where pretlista is null
        defaultPortofoliuShouldNotBeFound("pretlista.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista is greater than or equal to DEFAULT_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.greaterThanOrEqual=" + DEFAULT_PRETLISTA);

        // Get all the portofoliuList where pretlista is greater than or equal to UPDATED_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.greaterThanOrEqual=" + UPDATED_PRETLISTA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista is less than or equal to DEFAULT_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.lessThanOrEqual=" + DEFAULT_PRETLISTA);

        // Get all the portofoliuList where pretlista is less than or equal to SMALLER_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.lessThanOrEqual=" + SMALLER_PRETLISTA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista is less than DEFAULT_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.lessThan=" + DEFAULT_PRETLISTA);

        // Get all the portofoliuList where pretlista is less than UPDATED_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.lessThan=" + UPDATED_PRETLISTA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretlistaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretlista is greater than DEFAULT_PRETLISTA
        defaultPortofoliuShouldNotBeFound("pretlista.greaterThan=" + DEFAULT_PRETLISTA);

        // Get all the portofoliuList where pretlista is greater than SMALLER_PRETLISTA
        defaultPortofoliuShouldBeFound("pretlista.greaterThan=" + SMALLER_PRETLISTA);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard equals to DEFAULT_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.equals=" + DEFAULT_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard equals to UPDATED_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.equals=" + UPDATED_DISCOUNTSTANDARD);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard not equals to DEFAULT_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.notEquals=" + DEFAULT_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard not equals to UPDATED_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.notEquals=" + UPDATED_DISCOUNTSTANDARD);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard in DEFAULT_DISCOUNTSTANDARD or UPDATED_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.in=" + DEFAULT_DISCOUNTSTANDARD + "," + UPDATED_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard equals to UPDATED_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.in=" + UPDATED_DISCOUNTSTANDARD);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard is not null
        defaultPortofoliuShouldBeFound("discountstandard.specified=true");

        // Get all the portofoliuList where discountstandard is null
        defaultPortofoliuShouldNotBeFound("discountstandard.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard is greater than or equal to DEFAULT_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.greaterThanOrEqual=" + DEFAULT_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard is greater than or equal to UPDATED_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.greaterThanOrEqual=" + UPDATED_DISCOUNTSTANDARD);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard is less than or equal to DEFAULT_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.lessThanOrEqual=" + DEFAULT_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard is less than or equal to SMALLER_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.lessThanOrEqual=" + SMALLER_DISCOUNTSTANDARD);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard is less than DEFAULT_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.lessThan=" + DEFAULT_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard is less than UPDATED_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.lessThan=" + UPDATED_DISCOUNTSTANDARD);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountstandardIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountstandard is greater than DEFAULT_DISCOUNTSTANDARD
        defaultPortofoliuShouldNotBeFound("discountstandard.greaterThan=" + DEFAULT_DISCOUNTSTANDARD);

        // Get all the portofoliuList where discountstandard is greater than SMALLER_DISCOUNTSTANDARD
        defaultPortofoliuShouldBeFound("discountstandard.greaterThan=" + SMALLER_DISCOUNTSTANDARD);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar equals to DEFAULT_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.equals=" + DEFAULT_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar equals to UPDATED_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.equals=" + UPDATED_DISCOUNTSUPLIMENTAR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar not equals to DEFAULT_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.notEquals=" + DEFAULT_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar not equals to UPDATED_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.notEquals=" + UPDATED_DISCOUNTSUPLIMENTAR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar in DEFAULT_DISCOUNTSUPLIMENTAR or UPDATED_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.in=" + DEFAULT_DISCOUNTSUPLIMENTAR + "," + UPDATED_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar equals to UPDATED_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.in=" + UPDATED_DISCOUNTSUPLIMENTAR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar is not null
        defaultPortofoliuShouldBeFound("discountsuplimentar.specified=true");

        // Get all the portofoliuList where discountsuplimentar is null
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar is greater than or equal to DEFAULT_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.greaterThanOrEqual=" + DEFAULT_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar is greater than or equal to UPDATED_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.greaterThanOrEqual=" + UPDATED_DISCOUNTSUPLIMENTAR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar is less than or equal to DEFAULT_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.lessThanOrEqual=" + DEFAULT_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar is less than or equal to SMALLER_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.lessThanOrEqual=" + SMALLER_DISCOUNTSUPLIMENTAR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar is less than DEFAULT_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.lessThan=" + DEFAULT_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar is less than UPDATED_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.lessThan=" + UPDATED_DISCOUNTSUPLIMENTAR);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDiscountsuplimentarIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where discountsuplimentar is greater than DEFAULT_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldNotBeFound("discountsuplimentar.greaterThan=" + DEFAULT_DISCOUNTSUPLIMENTAR);

        // Get all the portofoliuList where discountsuplimentar is greater than SMALLER_DISCOUNTSUPLIMENTAR
        defaultPortofoliuShouldBeFound("discountsuplimentar.greaterThan=" + SMALLER_DISCOUNTSUPLIMENTAR);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa equals to DEFAULT_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.equals=" + DEFAULT_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa equals to UPDATED_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.equals=" + UPDATED_TRUSALEGISLATIVA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa not equals to DEFAULT_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.notEquals=" + DEFAULT_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa not equals to UPDATED_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.notEquals=" + UPDATED_TRUSALEGISLATIVA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa in DEFAULT_TRUSALEGISLATIVA or UPDATED_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.in=" + DEFAULT_TRUSALEGISLATIVA + "," + UPDATED_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa equals to UPDATED_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.in=" + UPDATED_TRUSALEGISLATIVA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa is not null
        defaultPortofoliuShouldBeFound("trusalegislativa.specified=true");

        // Get all the portofoliuList where trusalegislativa is null
        defaultPortofoliuShouldNotBeFound("trusalegislativa.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa is greater than or equal to DEFAULT_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.greaterThanOrEqual=" + DEFAULT_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa is greater than or equal to UPDATED_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.greaterThanOrEqual=" + UPDATED_TRUSALEGISLATIVA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa is less than or equal to DEFAULT_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.lessThanOrEqual=" + DEFAULT_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa is less than or equal to SMALLER_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.lessThanOrEqual=" + SMALLER_TRUSALEGISLATIVA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa is less than DEFAULT_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.lessThan=" + DEFAULT_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa is less than UPDATED_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.lessThan=" + UPDATED_TRUSALEGISLATIVA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTrusalegislativaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where trusalegislativa is greater than DEFAULT_TRUSALEGISLATIVA
        defaultPortofoliuShouldNotBeFound("trusalegislativa.greaterThan=" + DEFAULT_TRUSALEGISLATIVA);

        // Get all the portofoliuList where trusalegislativa is greater than SMALLER_TRUSALEGISLATIVA
        defaultPortofoliuShouldBeFound("trusalegislativa.greaterThan=" + SMALLER_TRUSALEGISLATIVA);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal equals to DEFAULT_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.equals=" + DEFAULT_PRETFINAL);

        // Get all the portofoliuList where pretfinal equals to UPDATED_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.equals=" + UPDATED_PRETFINAL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal not equals to DEFAULT_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.notEquals=" + DEFAULT_PRETFINAL);

        // Get all the portofoliuList where pretfinal not equals to UPDATED_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.notEquals=" + UPDATED_PRETFINAL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal in DEFAULT_PRETFINAL or UPDATED_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.in=" + DEFAULT_PRETFINAL + "," + UPDATED_PRETFINAL);

        // Get all the portofoliuList where pretfinal equals to UPDATED_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.in=" + UPDATED_PRETFINAL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal is not null
        defaultPortofoliuShouldBeFound("pretfinal.specified=true");

        // Get all the portofoliuList where pretfinal is null
        defaultPortofoliuShouldNotBeFound("pretfinal.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal is greater than or equal to DEFAULT_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.greaterThanOrEqual=" + DEFAULT_PRETFINAL);

        // Get all the portofoliuList where pretfinal is greater than or equal to UPDATED_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.greaterThanOrEqual=" + UPDATED_PRETFINAL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal is less than or equal to DEFAULT_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.lessThanOrEqual=" + DEFAULT_PRETFINAL);

        // Get all the portofoliuList where pretfinal is less than or equal to SMALLER_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.lessThanOrEqual=" + SMALLER_PRETFINAL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal is less than DEFAULT_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.lessThan=" + DEFAULT_PRETFINAL);

        // Get all the portofoliuList where pretfinal is less than UPDATED_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.lessThan=" + UPDATED_PRETFINAL);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByPretfinalIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where pretfinal is greater than DEFAULT_PRETFINAL
        defaultPortofoliuShouldNotBeFound("pretfinal.greaterThan=" + DEFAULT_PRETFINAL);

        // Get all the portofoliuList where pretfinal is greater than SMALLER_PRETFINAL
        defaultPortofoliuShouldBeFound("pretfinal.greaterThan=" + SMALLER_PRETFINAL);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit equals to DEFAULT_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.equals=" + DEFAULT_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit equals to UPDATED_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.equals=" + UPDATED_AVANSPLATIT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit not equals to DEFAULT_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.notEquals=" + DEFAULT_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit not equals to UPDATED_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.notEquals=" + UPDATED_AVANSPLATIT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit in DEFAULT_AVANSPLATIT or UPDATED_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.in=" + DEFAULT_AVANSPLATIT + "," + UPDATED_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit equals to UPDATED_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.in=" + UPDATED_AVANSPLATIT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit is not null
        defaultPortofoliuShouldBeFound("avansplatit.specified=true");

        // Get all the portofoliuList where avansplatit is null
        defaultPortofoliuShouldNotBeFound("avansplatit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit is greater than or equal to DEFAULT_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.greaterThanOrEqual=" + DEFAULT_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit is greater than or equal to UPDATED_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.greaterThanOrEqual=" + UPDATED_AVANSPLATIT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit is less than or equal to DEFAULT_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.lessThanOrEqual=" + DEFAULT_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit is less than or equal to SMALLER_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.lessThanOrEqual=" + SMALLER_AVANSPLATIT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit is less than DEFAULT_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.lessThan=" + DEFAULT_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit is less than UPDATED_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.lessThan=" + UPDATED_AVANSPLATIT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByAvansplatitIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where avansplatit is greater than DEFAULT_AVANSPLATIT
        defaultPortofoliuShouldNotBeFound("avansplatit.greaterThan=" + DEFAULT_AVANSPLATIT);

        // Get all the portofoliuList where avansplatit is greater than SMALLER_AVANSPLATIT
        defaultPortofoliuShouldBeFound("avansplatit.greaterThan=" + SMALLER_AVANSPLATIT);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata equals to DEFAULT_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.equals=" + DEFAULT_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata equals to UPDATED_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.equals=" + UPDATED_RESTDEPLATA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata not equals to DEFAULT_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.notEquals=" + DEFAULT_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata not equals to UPDATED_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.notEquals=" + UPDATED_RESTDEPLATA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata in DEFAULT_RESTDEPLATA or UPDATED_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.in=" + DEFAULT_RESTDEPLATA + "," + UPDATED_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata equals to UPDATED_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.in=" + UPDATED_RESTDEPLATA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata is not null
        defaultPortofoliuShouldBeFound("restdeplata.specified=true");

        // Get all the portofoliuList where restdeplata is null
        defaultPortofoliuShouldNotBeFound("restdeplata.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata is greater than or equal to DEFAULT_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.greaterThanOrEqual=" + DEFAULT_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata is greater than or equal to UPDATED_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.greaterThanOrEqual=" + UPDATED_RESTDEPLATA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata is less than or equal to DEFAULT_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.lessThanOrEqual=" + DEFAULT_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata is less than or equal to SMALLER_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.lessThanOrEqual=" + SMALLER_RESTDEPLATA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata is less than DEFAULT_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.lessThan=" + DEFAULT_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata is less than UPDATED_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.lessThan=" + UPDATED_RESTDEPLATA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRestdeplataIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where restdeplata is greater than DEFAULT_RESTDEPLATA
        defaultPortofoliuShouldNotBeFound("restdeplata.greaterThan=" + DEFAULT_RESTDEPLATA);

        // Get all the portofoliuList where restdeplata is greater than SMALLER_RESTDEPLATA
        defaultPortofoliuShouldBeFound("restdeplata.greaterThan=" + SMALLER_RESTDEPLATA);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid equals to DEFAULT_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.equals=" + DEFAULT_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid equals to UPDATED_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.equals=" + UPDATED_CUSTOMERTRXID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid not equals to DEFAULT_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.notEquals=" + DEFAULT_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid not equals to UPDATED_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.notEquals=" + UPDATED_CUSTOMERTRXID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid in DEFAULT_CUSTOMERTRXID or UPDATED_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.in=" + DEFAULT_CUSTOMERTRXID + "," + UPDATED_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid equals to UPDATED_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.in=" + UPDATED_CUSTOMERTRXID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid is not null
        defaultPortofoliuShouldBeFound("customertrxid.specified=true");

        // Get all the portofoliuList where customertrxid is null
        defaultPortofoliuShouldNotBeFound("customertrxid.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid is greater than or equal to DEFAULT_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.greaterThanOrEqual=" + DEFAULT_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid is greater than or equal to UPDATED_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.greaterThanOrEqual=" + UPDATED_CUSTOMERTRXID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid is less than or equal to DEFAULT_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.lessThanOrEqual=" + DEFAULT_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid is less than or equal to SMALLER_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.lessThanOrEqual=" + SMALLER_CUSTOMERTRXID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid is less than DEFAULT_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.lessThan=" + DEFAULT_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid is less than UPDATED_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.lessThan=" + UPDATED_CUSTOMERTRXID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByCustomertrxidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where customertrxid is greater than DEFAULT_CUSTOMERTRXID
        defaultPortofoliuShouldNotBeFound("customertrxid.greaterThan=" + DEFAULT_CUSTOMERTRXID);

        // Get all the portofoliuList where customertrxid is greater than SMALLER_CUSTOMERTRXID
        defaultPortofoliuShouldBeFound("customertrxid.greaterThan=" + SMALLER_CUSTOMERTRXID);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByRezcustidIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where rezcustid equals to DEFAULT_REZCUSTID
        defaultPortofoliuShouldBeFound("rezcustid.equals=" + DEFAULT_REZCUSTID);

        // Get all the portofoliuList where rezcustid equals to UPDATED_REZCUSTID
        defaultPortofoliuShouldNotBeFound("rezcustid.equals=" + UPDATED_REZCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRezcustidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where rezcustid not equals to DEFAULT_REZCUSTID
        defaultPortofoliuShouldNotBeFound("rezcustid.notEquals=" + DEFAULT_REZCUSTID);

        // Get all the portofoliuList where rezcustid not equals to UPDATED_REZCUSTID
        defaultPortofoliuShouldBeFound("rezcustid.notEquals=" + UPDATED_REZCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRezcustidIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where rezcustid in DEFAULT_REZCUSTID or UPDATED_REZCUSTID
        defaultPortofoliuShouldBeFound("rezcustid.in=" + DEFAULT_REZCUSTID + "," + UPDATED_REZCUSTID);

        // Get all the portofoliuList where rezcustid equals to UPDATED_REZCUSTID
        defaultPortofoliuShouldNotBeFound("rezcustid.in=" + UPDATED_REZCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRezcustidIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where rezcustid is not null
        defaultPortofoliuShouldBeFound("rezcustid.specified=true");

        // Get all the portofoliuList where rezcustid is null
        defaultPortofoliuShouldNotBeFound("rezcustid.specified=false");
    }
                @Test
    @Transactional
    public void getAllPortofoliusByRezcustidContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where rezcustid contains DEFAULT_REZCUSTID
        defaultPortofoliuShouldBeFound("rezcustid.contains=" + DEFAULT_REZCUSTID);

        // Get all the portofoliuList where rezcustid contains UPDATED_REZCUSTID
        defaultPortofoliuShouldNotBeFound("rezcustid.contains=" + UPDATED_REZCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByRezcustidNotContainsSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where rezcustid does not contain DEFAULT_REZCUSTID
        defaultPortofoliuShouldNotBeFound("rezcustid.doesNotContain=" + DEFAULT_REZCUSTID);

        // Get all the portofoliuList where rezcustid does not contain UPDATED_REZCUSTID
        defaultPortofoliuShouldBeFound("rezcustid.doesNotContain=" + UPDATED_REZCUSTID);
    }


    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid equals to DEFAULT_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.equals=" + DEFAULT_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid equals to UPDATED_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.equals=" + UPDATED_SOLDCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid not equals to DEFAULT_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.notEquals=" + DEFAULT_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid not equals to UPDATED_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.notEquals=" + UPDATED_SOLDCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid in DEFAULT_SOLDCUSTID or UPDATED_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.in=" + DEFAULT_SOLDCUSTID + "," + UPDATED_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid equals to UPDATED_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.in=" + UPDATED_SOLDCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid is not null
        defaultPortofoliuShouldBeFound("soldcustid.specified=true");

        // Get all the portofoliuList where soldcustid is null
        defaultPortofoliuShouldNotBeFound("soldcustid.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid is greater than or equal to DEFAULT_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.greaterThanOrEqual=" + DEFAULT_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid is greater than or equal to UPDATED_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.greaterThanOrEqual=" + UPDATED_SOLDCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid is less than or equal to DEFAULT_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.lessThanOrEqual=" + DEFAULT_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid is less than or equal to SMALLER_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.lessThanOrEqual=" + SMALLER_SOLDCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsLessThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid is less than DEFAULT_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.lessThan=" + DEFAULT_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid is less than UPDATED_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.lessThan=" + UPDATED_SOLDCUSTID);
    }

    @Test
    @Transactional
    public void getAllPortofoliusBySoldcustidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where soldcustid is greater than DEFAULT_SOLDCUSTID
        defaultPortofoliuShouldNotBeFound("soldcustid.greaterThan=" + DEFAULT_SOLDCUSTID);

        // Get all the portofoliuList where soldcustid is greater than SMALLER_SOLDCUSTID
        defaultPortofoliuShouldBeFound("soldcustid.greaterThan=" + SMALLER_SOLDCUSTID);
    }


    @Test
    @Transactional
    public void getAllPortofoliusByProformaIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where proforma equals to DEFAULT_PROFORMA
        defaultPortofoliuShouldBeFound("proforma.equals=" + DEFAULT_PROFORMA);

        // Get all the portofoliuList where proforma equals to UPDATED_PROFORMA
        defaultPortofoliuShouldNotBeFound("proforma.equals=" + UPDATED_PROFORMA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByProformaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where proforma not equals to DEFAULT_PROFORMA
        defaultPortofoliuShouldNotBeFound("proforma.notEquals=" + DEFAULT_PROFORMA);

        // Get all the portofoliuList where proforma not equals to UPDATED_PROFORMA
        defaultPortofoliuShouldBeFound("proforma.notEquals=" + UPDATED_PROFORMA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByProformaIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where proforma in DEFAULT_PROFORMA or UPDATED_PROFORMA
        defaultPortofoliuShouldBeFound("proforma.in=" + DEFAULT_PROFORMA + "," + UPDATED_PROFORMA);

        // Get all the portofoliuList where proforma equals to UPDATED_PROFORMA
        defaultPortofoliuShouldNotBeFound("proforma.in=" + UPDATED_PROFORMA);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByProformaIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where proforma is not null
        defaultPortofoliuShouldBeFound("proforma.specified=true");

        // Get all the portofoliuList where proforma is null
        defaultPortofoliuShouldNotBeFound("proforma.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTransportIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where transport equals to DEFAULT_TRANSPORT
        defaultPortofoliuShouldBeFound("transport.equals=" + DEFAULT_TRANSPORT);

        // Get all the portofoliuList where transport equals to UPDATED_TRANSPORT
        defaultPortofoliuShouldNotBeFound("transport.equals=" + UPDATED_TRANSPORT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTransportIsNotEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where transport not equals to DEFAULT_TRANSPORT
        defaultPortofoliuShouldNotBeFound("transport.notEquals=" + DEFAULT_TRANSPORT);

        // Get all the portofoliuList where transport not equals to UPDATED_TRANSPORT
        defaultPortofoliuShouldBeFound("transport.notEquals=" + UPDATED_TRANSPORT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTransportIsInShouldWork() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where transport in DEFAULT_TRANSPORT or UPDATED_TRANSPORT
        defaultPortofoliuShouldBeFound("transport.in=" + DEFAULT_TRANSPORT + "," + UPDATED_TRANSPORT);

        // Get all the portofoliuList where transport equals to UPDATED_TRANSPORT
        defaultPortofoliuShouldNotBeFound("transport.in=" + UPDATED_TRANSPORT);
    }

    @Test
    @Transactional
    public void getAllPortofoliusByTransportIsNullOrNotNull() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);

        // Get all the portofoliuList where transport is not null
        defaultPortofoliuShouldBeFound("transport.specified=true");

        // Get all the portofoliuList where transport is null
        defaultPortofoliuShouldNotBeFound("transport.specified=false");
    }

    @Test
    @Transactional
    public void getAllPortofoliusByDealerIsEqualToSomething() throws Exception {
        // Initialize the database
        portofoliuRepository.saveAndFlush(portofoliu);
        Dealer dealer = DealerResourceIT.createEntity(em);
        em.persist(dealer);
        em.flush();
        portofoliu.addDealer(dealer);
        portofoliuRepository.saveAndFlush(portofoliu);
        Long dealerId = dealer.getId();

        // Get all the portofoliuList where dealer equals to dealerId
        defaultPortofoliuShouldBeFound("dealerId.equals=" + dealerId);

        // Get all the portofoliuList where dealer equals to dealerId + 1
        defaultPortofoliuShouldNotBeFound("dealerId.equals=" + (dealerId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPortofoliuShouldBeFound(String filter) throws Exception {
        restPortofoliuMockMvc.perform(get("/api/portofolius?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(portofoliu.getId().intValue())))
            .andExpect(jsonPath("$.[*].htrocarno").value(hasItem(DEFAULT_HTROCARNO)))
            .andExpect(jsonPath("$.[*].dealer").value(hasItem(DEFAULT_DEALER)))
            .andExpect(jsonPath("$.[*].datarezsaufactura").value(hasItem(DEFAULT_DATAREZSAUFACTURA)))
            .andExpect(jsonPath("$.[*].dataexpirare").value(hasItem(DEFAULT_DATAEXPIRARE)))
            .andExpect(jsonPath("$.[*].resdealerid").value(hasItem(DEFAULT_RESDEALERID)))
            .andExpect(jsonPath("$.[*].tiplinie").value(hasItem(DEFAULT_TIPLINIE)))
            .andExpect(jsonPath("$.[*].locatie").value(hasItem(DEFAULT_LOCATIE)))
            .andExpect(jsonPath("$.[*].lunaproductie").value(hasItem(DEFAULT_LUNAPRODUCTIE)))
            .andExpect(jsonPath("$.[*].lunasosireintara").value(hasItem(DEFAULT_LUNASOSIREINTARA)))
            .andExpect(jsonPath("$.[*].codmodel").value(hasItem(DEFAULT_CODMODEL)))
            .andExpect(jsonPath("$.[*].tipautovehicul").value(hasItem(DEFAULT_TIPAUTOVEHICUL)))
            .andExpect(jsonPath("$.[*].codculoareext").value(hasItem(DEFAULT_CODCULOAREEXT)))
            .andExpect(jsonPath("$.[*].culoareexterior").value(hasItem(DEFAULT_CULOAREEXTERIOR)))
            .andExpect(jsonPath("$.[*].culoareIntegererior").value(hasItem(DEFAULT_CULOARE_INTEGERERIOR)))
            .andExpect(jsonPath("$.[*].observatii").value(hasItem(DEFAULT_OBSERVATII)))
            .andExpect(jsonPath("$.[*].numeclient").value(hasItem(DEFAULT_NUMECLIENT)))
            .andExpect(jsonPath("$.[*].numevanzator").value(hasItem(DEFAULT_NUMEVANZATOR)))
            .andExpect(jsonPath("$.[*].vin").value(hasItem(DEFAULT_VIN)))
            .andExpect(jsonPath("$.[*].engineno").value(hasItem(DEFAULT_ENGINENO)))
            .andExpect(jsonPath("$.[*].anfabricatiecfciv").value(hasItem(DEFAULT_ANFABRICATIECFCIV)))
            .andExpect(jsonPath("$.[*].omologareindividuala").value(hasItem(DEFAULT_OMOLOGAREINDIVIDUALA)))
            .andExpect(jsonPath("$.[*].pretlista").value(hasItem(DEFAULT_PRETLISTA)))
            .andExpect(jsonPath("$.[*].discountstandard").value(hasItem(DEFAULT_DISCOUNTSTANDARD)))
            .andExpect(jsonPath("$.[*].discountsuplimentar").value(hasItem(DEFAULT_DISCOUNTSUPLIMENTAR)))
            .andExpect(jsonPath("$.[*].trusalegislativa").value(hasItem(DEFAULT_TRUSALEGISLATIVA)))
            .andExpect(jsonPath("$.[*].pretfinal").value(hasItem(DEFAULT_PRETFINAL)))
            .andExpect(jsonPath("$.[*].avansplatit").value(hasItem(DEFAULT_AVANSPLATIT)))
            .andExpect(jsonPath("$.[*].restdeplata").value(hasItem(DEFAULT_RESTDEPLATA)))
            .andExpect(jsonPath("$.[*].customertrxid").value(hasItem(DEFAULT_CUSTOMERTRXID)))
            .andExpect(jsonPath("$.[*].rezcustid").value(hasItem(DEFAULT_REZCUSTID)))
            .andExpect(jsonPath("$.[*].soldcustid").value(hasItem(DEFAULT_SOLDCUSTID)))
            .andExpect(jsonPath("$.[*].proforma").value(hasItem(DEFAULT_PROFORMA.booleanValue())))
            .andExpect(jsonPath("$.[*].transport").value(hasItem(DEFAULT_TRANSPORT.booleanValue())));

        // Check, that the count call also returns 1
        restPortofoliuMockMvc.perform(get("/api/portofolius/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPortofoliuShouldNotBeFound(String filter) throws Exception {
        restPortofoliuMockMvc.perform(get("/api/portofolius?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPortofoliuMockMvc.perform(get("/api/portofolius/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPortofoliu() throws Exception {
        // Get the portofoliu
        restPortofoliuMockMvc.perform(get("/api/portofolius/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}

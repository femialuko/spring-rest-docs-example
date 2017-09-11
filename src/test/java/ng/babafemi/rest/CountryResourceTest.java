package ng.babafemi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ng.babafemi.SpringRestDocumentationApplication;
import ng.babafemi.domain.Country;
import ng.babafemi.service.CountryService;
import org.junit.Before;
import org.junit.Rule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import org.springframework.restdocs.payload.FieldDescriptor;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import org.springframework.restdocs.request.RequestDocumentation;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author babafemi
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringRestDocumentationApplication.class)
public class CountryResourceTest {
    
    @Autowired
    private CountryService countryService;
    
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
            "target/generated-snippets"
    );

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    private Country country;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        country = new Country();
        country.setId(1L);
        country.setName("Nigeria");
        country.setCapital("Abuja");
        
    }
    
    @Test
    public void runTests() throws Exception{
        saveCountry(country);
        getCountries();
        getCountryById(country.getId());
    }
    
    public void saveCountry(Country country) throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("saveCountry");
        
        document.snippets(
                requestFields(countryFields(false)),
                responseFields(countryFields(false))
        );

        this.mockMvc.perform(post("/country")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(country))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document);
    }

    public void getCountries() throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("getCountries");

        document.snippets(
                responseFields(countryFields(true))
        );

        MvcResult result = this.mockMvc.perform(get("/country")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("[*].id").isNotEmpty())
                .andExpect(jsonPath("[*].name").isNotEmpty())
                .andExpect(jsonPath("[*].capital").isNotEmpty())
                .andDo(document)
                .andReturn();
    }
    
    public void getCountryById(Long countryId) throws Exception {

        RestDocumentationResultHandler document = documentPrettyPrintReqResp("getCountryById");

        document.snippets(
                RequestDocumentation.pathParameters(parameterWithName("id").description("The id of the country")),
                responseFields(countryFields(false))
        );

        MvcResult result = this.mockMvc.perform(get("/country/{id}", countryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").isNotEmpty())
                .andExpect(jsonPath("capital").isNotEmpty())
                .andDo(document)
                .andReturn();
    }
    
    private RestDocumentationResultHandler documentPrettyPrintReqResp(String useCase) {
        return document(useCase,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));
    }

    private static FieldDescriptor[] countryFields(boolean isJsonArray) {
        return isJsonArray
                ? new FieldDescriptor[]{
                    fieldWithPath("[]").description("List of countries"),
                    fieldWithPath("[].name").description("Name of the country"),
                    fieldWithPath("[].capital").description("capital for the country")
                }
                : new FieldDescriptor[]{
                    fieldWithPath("name").description("Name of the country"),
                    fieldWithPath("capital").description("capital for the country"),
                    fieldWithPath("id").description("id of the country")
                };
    }
    
}

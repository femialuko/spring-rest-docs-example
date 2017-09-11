package ng.babafemi.service;

import java.util.ArrayList;
import java.util.List;
import ng.babafemi.SpringRestDocumentationApplication;
import ng.babafemi.domain.Country;
import ng.babafemi.repository.CountryRepository;
import ng.babafemi.service.impl.CountryServiceImpl;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author babafemi
 */
@SpringBootTest(classes = SpringRestDocumentationApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryServiceTest {
    
    private Country country;
    
    @Mock
    private CountryRepository countryRepository;
    
    @InjectMocks
    private CountryServiceImpl countryService;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        country = new Country();
        country.setCapital("Abuja");
        country.setName("Nigeria");
        country.setId(1L);
        
    }
    
    @Test
    public void testSaveCountry(){
        when(countryRepository.save(country)).thenReturn(country);
        Country result = countryService.save(country);
        assertEquals("Nigeria", result.getName());
        assertEquals("Abuja", result.getCapital());
        assertEquals(1L, result.getId(), Long.SIZE);
        
    }
    
    @Test
    public void testGetCountry(){
        when(countryRepository.findOne(1L)).thenReturn(country);
        Country result = countryService.get(1L);
        assertEquals("Nigeria", result.getName());
        assertEquals("Abuja", result.getCapital());
        assertEquals(1L, result.getId(), Long.SIZE);
        
    }
    
    @Test
    public void testGetAllCountries(){
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        when(countryRepository.findAll()).thenReturn(countries);
        List<Country> result = countryService.getAll();
        assertEquals(1L, result.size());
        assertEquals("Nigeria", result.get(0).getName());
        assertEquals("Abuja", result.get(0).getCapital());
        assertEquals(1L, result.get(0).getId(), Long.SIZE);
    }
    
}

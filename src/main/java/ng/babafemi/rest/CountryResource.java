package ng.babafemi.rest;

import ng.babafemi.domain.Country;
import ng.babafemi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author babafemi
 */
@RestController
@RequestMapping("/country")
public class CountryResource {
    
    @Autowired
    private CountryService countryService;
    
    @GetMapping
    public ResponseEntity<?> getCountries(){
        return ResponseEntity.ok(countryService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getACountryById(@PathVariable("id") Long id){
        Country country = countryService.get(id);
        if(country == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This country was not found");
        return ResponseEntity.ok(country);
    }

    @PostMapping
     ResponseEntity<?> saveCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.save(country));
        
    }
}

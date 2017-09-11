package ng.babafemi.service;

import java.util.List;
import ng.babafemi.domain.Country;

/**
 *
 * @author babafemi
 */
public interface CountryService {
    public Country save(Country country);
    
    public Country get(Long id);
    
    public List<Country> getAll();
}

package ng.babafemi.service.impl;

import java.util.List;
import ng.babafemi.domain.Country;
import ng.babafemi.repository.CountryRepository;
import ng.babafemi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author babafemi
 */
@Service
public class CountryServiceImpl implements CountryService{
    
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country get(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
    
}

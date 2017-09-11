package ng.babafemi.repository;

import ng.babafemi.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author babafemi
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
    
}

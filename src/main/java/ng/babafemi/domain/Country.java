package ng.babafemi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author babafemi
 */
@Entity
public class Country{
    
    @Id
    private Long id;
    
    private String name;
    
    private String capital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}

package mk.ukim.finki.emtlabs.service.Impl;

import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.repository.CountryRepository;
import mk.ukim.finki.emtlabs.service.CountryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Country findByName(String name) {
        return this.countryRepository.findByName(name);
    }

    @Override
    @Transactional
    public Country save(String name, String continent) throws Exception {
        if(name==null || name.isEmpty())
            throw new Exception();
        Country country = new Country(name, continent);
        this.countryRepository.deleteByName(name);
        this.countryRepository.save(country);
        return country;
    }

    @Override
    @Transactional
    public Optional<Country> edit(Long id, String name, String continent) throws Exception {
        Country country = this.countryRepository.findById(id).orElseThrow(Exception::new);
        country.setName(name);
        country.setContinent(continent);

        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}

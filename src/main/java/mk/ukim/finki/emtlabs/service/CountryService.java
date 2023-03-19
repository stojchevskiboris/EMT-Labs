package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Country findByName(String name);
    Country save(String name, String continent) throws Exception;
    Optional<Country> edit(Long id, String name, String continent) throws Exception;
    void deleteById(Long id);
}

package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);
    Author save(String name, String surname, Long countryId) throws Exception;
    Optional<Author> edit(Long id, String name, String surname, Long countryId) throws Exception;
    void deleteById(Long id);
}

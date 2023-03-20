package mk.ukim.finki.emtlabs.service.Impl;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.repository.AuthorRepository;
import mk.ukim.finki.emtlabs.repository.CountryRepository;
import mk.ukim.finki.emtlabs.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    @Transactional
    public Author save(String name, String surname, Long countryId) throws Exception {
        if(name==null || name.isEmpty())
            throw new Exception();
        Country country = this.countryRepository.findById(countryId).orElseThrow(Exception::new);
        Author author = new Author(name,surname,country);
        this.authorRepository.deleteByName(name);
        this.authorRepository.save(author);
        return author;
    }

    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) throws Exception {
        Author author = this.authorRepository.findById(id).orElseThrow(Exception::new);
        author.setName(name);
        author.setSurname(surname);
        Country country = this.countryRepository.findById(countryId).orElseThrow(Exception::new);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}

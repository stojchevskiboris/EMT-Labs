package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    Book save(String name, Category category, Long authorId, Integer availableCopies) throws Exception;
    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) throws Exception;
    void deleteById(Long id);
}

package mk.ukim.finki.emtlabs.service.Impl;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.enums.Category;
import mk.ukim.finki.emtlabs.repository.AuthorRepository;
import mk.ukim.finki.emtlabs.repository.BookRepository;
import mk.ukim.finki.emtlabs.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Book save(String name, Category category, Long authorId, Integer availableCopies) throws Exception {
        if(name==null || name.isEmpty())
            throw new Exception();
        Author author = this.authorRepository.findById(authorId).orElseThrow(Exception::new);
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.deleteByName(name);
        this.bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) throws Exception {
        Book book = this.bookRepository.findById(id).orElseThrow(Exception::new);
        book.setName(name);
        book.setCategory(category);

        Author author = this.authorRepository.findById(authorId).orElseThrow(Exception::new);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}

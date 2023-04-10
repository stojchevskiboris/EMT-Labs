package mk.ukim.finki.emtlabs.service.Impl;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.dto.BookDto;
import mk.ukim.finki.emtlabs.model.enums.Category;
import mk.ukim.finki.emtlabs.repository.AuthorRepository;
import mk.ukim.finki.emtlabs.repository.BookRepository;
import mk.ukim.finki.emtlabs.service.BookService;
import org.springframework.expression.ExpressionException;
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
    public Optional<Book> mark(Long id) throws Exception {
        Book book = this.bookRepository.findById(id).orElseThrow(Exception::new);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    @Transactional
    public Book save(String name, Category category, Long author, Integer availableCopies) throws Exception {
        if(name==null || name.isEmpty())
            throw new Exception();
        Author author1 = this.authorRepository.findById(author).orElseThrow(Exception::new);
        Book book = new Book(name, category, author1, availableCopies);
        this.bookRepository.deleteByName(name);
        this.bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies) throws Exception {
        Book book = this.bookRepository.findById(id).orElseThrow(Exception::new);
        book.setName(name);
        book.setCategory(category);

        Author author1 = this.authorRepository.findById(author).orElseThrow(Exception::new);
        book.setAuthor(author1);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> saveDto(BookDto bookDto) throws Exception {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(Exception::new);
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> editDto(Long id, BookDto bookDto) throws Exception {
        Book book = this.bookRepository.findById(id).orElseThrow(Exception::new);
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(Exception::new);

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }



    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}

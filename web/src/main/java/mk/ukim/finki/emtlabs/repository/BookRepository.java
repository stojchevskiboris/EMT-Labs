package mk.ukim.finki.emtlabs.repository;

import mk.ukim.finki.emtlabs.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    void deleteByName(String name);
}

package mk.ukim.finki.emtlabs.repository;

import mk.ukim.finki.emtlabs.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByName(String name);
    Optional<Author> deleteByName(String name);

}

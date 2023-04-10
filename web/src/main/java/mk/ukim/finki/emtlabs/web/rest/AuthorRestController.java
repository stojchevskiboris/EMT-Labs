package mk.ukim.finki.emtlabs.web.rest;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.dto.AuthorDto;
import mk.ukim.finki.emtlabs.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping(value = {"","/", "/list"})
    public List<Author> listAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id).map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }

    // DTO MAPPINGS
    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) throws Exception {
        return this.authorService.saveDto(authorDto).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody AuthorDto authorDto) throws Exception {
        return this.authorService.editDto(id, authorDto).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // PARAMETER MAPPINGS
    @PostMapping("/addAuthor")
    public ResponseEntity<Author> saveAuthor(@RequestParam String name,
                                         @RequestParam String surname,
                                         @RequestParam Long countryId) throws Exception {

        this.authorService.save(name, surname, countryId);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/editAuthor")
    public ResponseEntity<Author> editAuthor(@RequestParam Long id,
                                         @RequestParam String name,
                                         @RequestParam String surname,
                                         @RequestParam Long countryId) throws Exception {

        this.authorService.edit(id, name, surname, countryId);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.authorService.deleteById(id);
        if( this.authorService.findById(id).isEmpty() )
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}

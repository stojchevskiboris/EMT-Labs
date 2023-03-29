package mk.ukim.finki.emtlabs.web.rest;

import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.dto.BookDto;
import mk.ukim.finki.emtlabs.model.enums.Category;
import mk.ukim.finki.emtlabs.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"/", "/list"})
    public List<Book> listBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id).map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) throws Exception {
        return this.bookService.saveDto(bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) throws Exception {
        return this.bookService.editDto(id, bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> saveBook(@RequestParam String name,
                                         @RequestParam String category,
                                         @RequestParam Long author,
                                         @RequestParam Integer availableCopies) throws Exception {

        this.bookService.save(name, Category.valueOf(category), author, availableCopies);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/editBook")
    public ResponseEntity<Book> editBook(@RequestParam Long id,
                                         @RequestParam String name,
                                         @RequestParam String category,
                                         @RequestParam Long author,
                                         @RequestParam Integer availableCopies) throws Exception {

        this.bookService.edit(id, name, Category.valueOf(category), author, availableCopies);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if( this.bookService.findById(id).isEmpty() )
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}

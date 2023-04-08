package mk.ukim.finki.emtlabs.web.controller;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.enums.Category;
import mk.ukim.finki.emtlabs.service.AuthorService;
import mk.ukim.finki.emtlabs.service.BookService;
import mk.ukim.finki.emtlabs.service.CountryService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/books"})
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getBooksPage(Model model){
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = Arrays.stream(Category.values()).toList();
            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/error-page?error=BookNotFound";
    }

    @GetMapping("/add-form")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBook(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam Long author,
            @RequestParam Integer availableCopies) throws Exception {
        if (id != null) {
            this.bookService.edit(id, name, Category.valueOf(category), author, availableCopies);
        } else {
            this.bookService.save(name, Category.valueOf(category), author, availableCopies);
        }
        return "redirect:/books";
    }

}

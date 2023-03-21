package mk.ukim.finki.emtlabs.web.controller;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.enums.Category;
import mk.ukim.finki.emtlabs.service.AuthorService;
import mk.ukim.finki.emtlabs.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getAuthorsPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        this.authorService.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping("/edit-form/{id}")
    public String editAuthorPage(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            List<Country> countries = this.countryService.findAll();
            model.addAttribute("author", author);
            model.addAttribute("countries", countries);
            model.addAttribute("bodyContent", "add-author");
            return "master-template";
        }
        return "redirect:/error-page?error=AuthorNotFound";
    }

    @GetMapping("/add-form")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addAuthorPage(Model model) {
        List<Country> countries = this.countryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "add-author");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveAuthor(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Long country) throws Exception {
        if (id != null) {
            this.authorService.edit(id, name, surname, country);
        } else {
            this.authorService.save(name, surname, country);
        }
        return "redirect:/authors";
    }

}

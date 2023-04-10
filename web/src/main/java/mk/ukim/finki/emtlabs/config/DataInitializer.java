package mk.ukim.finki.emtlabs.config;

import lombok.Getter;
import mk.ukim.finki.emtlabs.service.AuthorService;
import mk.ukim.finki.emtlabs.service.CountryService;
import mk.ukim.finki.emtlabs.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static mk.ukim.finki.emtlabs.model.enums.Role.ROLE_ADMIN;

//ONLY IF USING H2 DATABASE

@Component
@Getter
public class DataInitializer {

    private final CountryService countryService;
    private final AuthorService authorService;
    private final UserService userService;

    public DataInitializer(CountryService countryService, AuthorService authorService, UserService userService) {
        this.countryService = countryService;
        this.authorService = authorService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() throws Exception {
        if (this.authorService.findAll().isEmpty()){
            this.countryService.save("Russia", "Europe");
            this.authorService.save("Lev Nikolaevich", "Tolstoy",1L);
        }
        if (this.userService.findByUsername("admin").isEmpty()){
            this.userService.register("admin","admin","admin","admin","admin",ROLE_ADMIN);
        }

    }
}

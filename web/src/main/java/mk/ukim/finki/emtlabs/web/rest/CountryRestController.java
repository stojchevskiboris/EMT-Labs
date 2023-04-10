package mk.ukim.finki.emtlabs.web.rest;

import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.dto.CountryDto;
import mk.ukim.finki.emtlabs.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = {"","/", "/list"})
    public List<Country> listCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id).map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }

    // DTO MAPPINGS
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto) throws Exception {
        return this.countryService.saveDto(countryDto).map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> edit(@PathVariable Long id, @RequestBody CountryDto countryDto) throws Exception {
        return this.countryService.editDto(id, countryDto).map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // PARAMETER MAPPINGS
    @PostMapping("/addCountry")
    public ResponseEntity<Country> saveCountry(@RequestParam String name,
                                             @RequestParam String continent) throws Exception {

        this.countryService.save(name, continent);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/editCountry")
    public ResponseEntity<Country> editCountry(@RequestParam Long id,
                                             @RequestParam String name,
                                             @RequestParam String continent) throws Exception {

        this.countryService.edit(id, name, continent);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.countryService.deleteById(id);
        if( this.countryService.findById(id).isEmpty() )
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}

package mk.ukim.finki.emtlabs.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlabs.model.enums.Category;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private Long countryId;

    public AuthorDto() {}

    public AuthorDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }
}

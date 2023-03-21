package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.enums.Role;
import mk.ukim.finki.emtlabs.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role) throws Exception;
}

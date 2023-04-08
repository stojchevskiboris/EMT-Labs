package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.User;

public interface AuthService {
    User login(String username, String password) throws Exception;
}

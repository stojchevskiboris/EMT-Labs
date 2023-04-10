package mk.ukim.finki.emtlabs.service.Impl;


import mk.ukim.finki.emtlabs.model.enums.Role;
import mk.ukim.finki.emtlabs.model.User;

import mk.ukim.finki.emtlabs.repository.UserRepository;
import mk.ukim.finki.emtlabs.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) throws Exception {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new Exception();
        if (!password.equals(repeatPassword))
            throw new Exception();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new Exception(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}

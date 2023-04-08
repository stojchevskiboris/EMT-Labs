package mk.ukim.finki.emtlabs.service.Impl;

import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.ShoppingCart;
import mk.ukim.finki.emtlabs.model.User;
import mk.ukim.finki.emtlabs.model.enums.ShoppingCartStatus;
import mk.ukim.finki.emtlabs.repository.ShoppingCartRepository;
import mk.ukim.finki.emtlabs.repository.UserRepository;
import mk.ukim.finki.emtlabs.service.BookService;
import mk.ukim.finki.emtlabs.service.ShoppingCartService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   BookService bookService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInShoppingCart(Long cartId) throws Exception {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new Exception();
        return this.shoppingCartRepository.findById(cartId).get().getBooks();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ExpressionException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addBookToShoppingCart(String username, Long bookId) throws Exception {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Book book = this.bookService.findById(bookId)
                .orElseThrow(() -> new ExpressionException("e"));
        if(shoppingCart.getBooks()
                .stream().filter(i -> i.getId().equals(bookId))
                .collect(Collectors.toList()).size() > 0)
            throw new Exception("Book already in cart");
        shoppingCart.getBooks().add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}


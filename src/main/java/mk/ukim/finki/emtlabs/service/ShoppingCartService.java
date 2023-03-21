package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Book> listAllBooksInShoppingCart(Long cartId) throws Exception;
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addBookToShoppingCart(String username, Long bookId) throws Exception;
}

package mk.ukim.finki.emtlabs.repository;

import mk.ukim.finki.emtlabs.model.ShoppingCart;
import mk.ukim.finki.emtlabs.model.User;
import mk.ukim.finki.emtlabs.model.enums.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}

package co.istad.chhaya.webmvc.repository;

import co.istad.chhaya.webmvc.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    // 1. Get all products where isAvailable = true (Pagination)
    Page<Product> findByIsAvailable(boolean isAvailable, Pageable pageable);

    // 2. Search by name containing keyword and isAvailable = true (Pagination)
    Page<Product> findByNameContainingIgnoreCaseAndIsAvailableTrue(String name, Pageable pageable);

}
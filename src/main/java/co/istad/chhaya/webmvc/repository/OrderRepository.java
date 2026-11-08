package co.istad.chhaya.webmvc.repository;

import co.istad.chhaya.webmvc.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}

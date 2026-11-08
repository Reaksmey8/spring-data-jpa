package co.istad.chhaya.webmvc.repository;

import co.istad.chhaya.webmvc.domain.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine, Integer> {
}

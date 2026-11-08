package co.istad.chhaya.webmvc.repository;

import co.istad.chhaya.webmvc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, String>{

}

package co.istad.chhaya.webmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Associate mapping with order
    @ManyToOne
    private Order order;
    // Associate mapping with product
    @ManyToOne
    private Product product;

    private Integer qty;
    private BigDecimal price;
    private Float discount;
}

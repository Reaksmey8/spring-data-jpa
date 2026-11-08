package co.istad.chhaya.webmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// 1. Make class to be POJO (Plain old java object)
@Getter
@Setter
@NoArgsConstructor
// Make class to be JPA Entity
@Entity
@Table(name = "categories")

public class Category {
    @Id
    @Column(length = 50, unique = true, nullable = false)
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}

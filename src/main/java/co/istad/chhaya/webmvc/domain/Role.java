package co.istad.chhaya.webmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users ;

    @Override
    public String getAuthority() {
        // ROLE_ADMIN, ROLE_BUSINESS, ROLE_CUSTOMER
        return "ROLE_" + this.name;
    }
}

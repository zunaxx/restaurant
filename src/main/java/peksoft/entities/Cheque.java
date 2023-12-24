package peksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cheque extends BaseEntity {

    private BigDecimal priceAverage;

    private LocalDate createdAt;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Menu>menus;


}

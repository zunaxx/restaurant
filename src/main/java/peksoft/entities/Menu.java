package peksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu extends BaseEntity{

    private String name;

    private String image;

    private BigDecimal price;

    private String description;

    private boolean isVegetarian;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "menus")
    private List<Cheque>cheques;

    @OneToOne
    private StopList stopList;

    @ManyToOne
    private SubCategory subCategory;

    public Menu(String menuName) {
        this.name=menuName;
    }
}

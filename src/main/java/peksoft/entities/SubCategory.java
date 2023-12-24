package peksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sub_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategory extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "subCategory")
    private List<Menu>menus;

    @ManyToOne
    private Category category;

}

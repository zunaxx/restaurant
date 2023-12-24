package peksoft.entities;

import peksoft.enums.RestType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant extends BaseEntity{

    private String name;

    private String location;

    @Enumerated(EnumType.STRING)
    private RestType restType;

    private int numberOfEmployees;

    private int service;

    @OneToMany(mappedBy = "restaurant")
    private List<User> users;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu>menus;

}

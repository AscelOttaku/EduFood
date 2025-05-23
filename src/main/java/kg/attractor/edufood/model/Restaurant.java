package kg.attractor.edufood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> dishes;
}

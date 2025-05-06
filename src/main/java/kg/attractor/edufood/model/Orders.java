package kg.attractor.edufood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    private List<OrderDish> orderDishes;
}

package com.sber.orm.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Ingredient {
    @Id
    @GeneratedValue
    private Integer id;
    private String product;
    private String amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", amount='" + amount + '\'' +
                ", recipe=" + (recipe != null ? recipe.getId() : null) +
                '}';
    }

}

package com.sber.orm.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Recipe {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ingredients=" + (!ingredients.isEmpty() ? ingredients : null) +
                '}';
    }

}

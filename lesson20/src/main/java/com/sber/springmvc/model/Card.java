package com.sber.springmvc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode
public class Card {
    @Id
    @GeneratedValue
    private Integer id;
    private String account;
    private Integer money;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", account=" + account +
                ", money=" + money +
                ", user=" + (user != null ? user.getName() : null) +
                '}';
    }
}

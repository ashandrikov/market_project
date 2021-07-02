package com.shandrikov.market.market_project.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shandrikov.market.market_project.category.Category;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "items")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @JsonIgnore
    @Lob
    @Column(name = "image")
    private String image;

}

package com.shandrikov.market.market_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

//    @NotEmpty(message = "Введите наименование")
//    @Length(min =2, message = "Наименование должно быть не короче 2 символов")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

//    @Column(name = "category_id")
//    private int category_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category_id;

//    @NotEmpty(message = "Введите описание")
    @Column(name = "description")
    private String description;

//    @Min(value = 0,message = "Цена не может быть ниже 0")
    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

//    @NotNull(message = "Выберите изображение")
    @JsonIgnore
    @Lob
    @Column(name = "image")
    private String image;

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

}

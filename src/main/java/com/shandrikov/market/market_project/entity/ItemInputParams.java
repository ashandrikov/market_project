package com.shandrikov.market.market_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemInputParams {

    private int id;

    @Length(min = 2, message = "Наименование должно быть не короче 2 символов")
    private String name;

    private Category category;

    @NotEmpty(message = "Введите описание")
    private String description;

    @Min(value = 0, message = "Цена должна быть больше 0")
    private int price;

    private User author;

    @NotNull(message = "Выберите изображение")
    @JsonIgnore
    private MultipartFile image;

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}

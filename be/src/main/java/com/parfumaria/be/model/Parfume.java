package com.parfumaria.be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parfume {
    @Id
    // auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // kode unik acak 36
    // @UuidGenerator
    private Integer id;
    private String name;
    private Integer stock;
    private Integer price;
    private String pict;
    @ManyToOne
    @JoinColumn(name = "id_Cateory", referencedColumnName = "id")
    private Category idCategory;

}

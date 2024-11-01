package com.project.aegis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "produk")
@Getter
@Setter
@NoArgsConstructor
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;

    private Double price;
    private int stock;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private String createdBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private String updatedBy;

    @Column(name = "is_delete", updatable = false)
    private boolean isDelete;

    @Column(name = "delete_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    private String deletedBy;

    public Produk(Long id, String name, Double price, int stock, String createdBy) {
        Date date = new Date();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = date;
        this.createdBy = createdBy;
        this.updatedAt = date;
        this.updatedBy = createdBy;
        this.isDelete = false;
        this.deletedAt = date;
        this.deletedBy = createdBy;
    }

    public Produk(String name, Double price, int stock, String createdBy) {
        Date date = new Date();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = date;
        this.createdBy = createdBy;
        this.updatedAt = date;
        this.updatedBy = createdBy;
        this.isDelete = false;
        this.deletedAt = date;
        this.deletedBy = createdBy;
    }
}

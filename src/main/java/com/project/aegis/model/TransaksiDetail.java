package com.project.aegis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(indexes = {
        @Index(columnList = "idProduk"),
        @Index(columnList = "name")
})
public class TransaksiDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idTransaksi;

    private Long idProduk;
    private String name;
    private double price;
    private int qty;

    public TransaksiDetail(String idTransaksi, Long idProduk, String name,
                                double price, int qty) {
        this.idTransaksi = idTransaksi;
        this.idProduk = idProduk;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
}

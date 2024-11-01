package com.project.aegis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransaksiDetailRequest {
    private Long idProduk;
    private String name;
    private double price;
    private int qty;
}

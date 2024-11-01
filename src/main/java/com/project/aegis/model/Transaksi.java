package com.project.aegis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "id"), @Index(columnList = "createdBy")})
public class Transaksi {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TransaksiDetail> orderDetails;

    private double subtotal;
    private double discount;
    private double total;
    private double payment;
    private double changes;

    @Column(updatable = false)
    private String createdBy;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Transaksi(String id, List<TransaksiDetail> orderDetails, double subtotal, double discount, double total, double payment,
                     double changes, String createdBy) {
        Date date = new Date();
        this.id = id;
        this.orderDetails = orderDetails;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
        this.payment = payment;
        this.changes = changes;
        this.createdBy = createdBy;
        this.createdAt = date;
        this.updatedBy = createdBy;
        this.updatedAt = date;
    }

    public Transaksi(String id) {
        this.id = id;
    }
}


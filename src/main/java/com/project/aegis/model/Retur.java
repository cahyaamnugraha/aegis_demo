package com.project.aegis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "id"), @Index(columnList = "createdBy")})
public class Retur {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    private Transaksi transaksi;

    @Column(updatable = false)
    private String createdBy;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Retur(String id, Transaksi transaksi, String createdBy) {
        Date date = new Date();
        this.id = id;
        this.transaksi = transaksi;
        this.createdBy = createdBy;
        this.createdAt = date;
        this.updatedBy = createdBy;
        this.updatedAt = date;
    }
}


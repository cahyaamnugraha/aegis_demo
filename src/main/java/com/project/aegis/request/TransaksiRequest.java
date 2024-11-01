package com.project.aegis.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransaksiRequest {
    private List<TransaksiDetailRequest> orderDetails;

    private double subtotal;
    private double discount;
    private double total;
    private double payment;
    private double changes;

    private String createdBy;
}

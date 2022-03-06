package com.unitech.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Column(name = "dp_acc")
    private String debitAccountNo;

    @Column(name = "cr_acc")
    private String creditAccountNo;

    private BigDecimal amount;

    @Column(name = "payment_id")
    private String paymentId;
}

package org.task.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "client_products")
public class ClientProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "account_number")
    public String accountNumber;
    @Column(name = "balance")
    public BigDecimal balance;
    @Column(name = "product_type")
    public String productType;
    @ManyToOne
    @JoinColumn(name = "userid")
    public User user;
}

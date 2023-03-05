package com.bankasarus.accounts.model;

import com.bankasarus.accounts.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        indexes = {
                @Index(name = "email_index", columnList = "email")
        }
)
public class Transaction {

    @Id
    @GeneratedValue
    private Long transactionId;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    private Double transactionAmt;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AccountType transactionType;
}

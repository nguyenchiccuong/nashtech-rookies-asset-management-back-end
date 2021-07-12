package com.nashtech.rootkies.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order payedOrder;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "payed_date")
    private LocalDateTime payDate;
}

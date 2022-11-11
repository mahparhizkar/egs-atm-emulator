package com.egs.bank.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "bank-app", name = "card")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "pin_type")
    private String pinType;

    @Column(name = "pin_value")
    private String pinValue;

    @Column(name = "remain_count_block")
    private int remainCountBlock;
}
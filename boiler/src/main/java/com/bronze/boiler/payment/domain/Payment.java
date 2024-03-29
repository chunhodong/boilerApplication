package com.bronze.boiler.payment.domain;

import com.bronze.boiler.common.BaseDate;
import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.order.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@DynamicInsert
public class Payment extends BaseDate {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "result_code")
    private String resultCode;
    @Column(name = "result_msg")
    private String resultMsg;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false,unique = true)
    private Orders order;
    @Column
    private String mid;
    @Column(name = "buyer_email")
    private String buyerEmail;
    @Column(name = "buyer_tel")
    private String buyerTel;
    @Column(name = "buyer_name")
    private String buyerName;
    @Column
    private String signature;
    @Column
    private String moid;
    @Column(name = "card_code")
    private String cardCode;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_quota")
    private Long cardQuota;
}


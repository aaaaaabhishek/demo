package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name="CustumerPlan")
public class CustumerPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="custumer_plan_id")
    private Long custumerPlanId;
   // @Column(name = "user_id", unique=true)
   @Column(name = "user_id")
    private String userId;
    private Boolean subscriptionActive;
    @Column(name="subscription_active_date")
    private LocalDateTime subscriptionActiveDate;
    @Column(name="subscription_expiration_date")
    private LocalDateTime subscriptionExpirationDate;
    @Column(name="number_of_days")
    private int numberOfDays;
    @Column(name="Plan_Name")
    private String PlanName;
    @Column(name="subscription_Price")
    private Integer subscriptionPrice;
}
package com.example.demo.Repositary;

import com.example.demo.entity.CustumerPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface  CustumerPlanRepository extends JpaRepository<CustumerPlan,Long> {
    List<CustumerPlan> findBySubscriptionActiveTrueAndSubscriptionExpirationDateBefore(LocalDateTime date);
    List<CustumerPlan>findBySubscriptionExpirationDateBefore(LocalDateTime date);
}
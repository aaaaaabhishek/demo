package com.example.demo.service;

import com.example.demo.Repositary.CustumerPlanRepository;
import com.example.demo.entity.CustumerPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustumerPlanService {

    private final CustumerPlanRepository custumerPlanRepository;

    @Autowired
    public CustumerPlanService(CustumerPlanRepository custumerPlanRepository) {
        this.custumerPlanRepository = custumerPlanRepository;
    }

    public CustumerPlan subscribeCustomerPlan(String userId, int duration,String PlanName,Integer subscriptionPrice) {
        CustumerPlan custumerPlan = new CustumerPlan();
        custumerPlan.setUserId(userId);
        custumerPlan.setSubscriptionPrice(subscriptionPrice);
        custumerPlan.setSubscriptionActive(true);
        custumerPlan.setPlanName(PlanName);
        custumerPlan.setSubscriptionActiveDate(LocalDateTime.now());
        custumerPlan.setSubscriptionExpirationDate(LocalDateTime.now().plusDays(duration));
        custumerPlan.setNumberOfDays(duration);
        return custumerPlanRepository.save(custumerPlan);
    }

    public CustumerPlan getCustomerPlan(Long customerPlanId) {
        Optional<CustumerPlan> optionalCustumerPlan = custumerPlanRepository.findById(customerPlanId);
        return optionalCustumerPlan.orElse(null);
    }

}

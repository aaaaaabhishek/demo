package com.example.demo.service;

import com.example.demo.Repositary.CustumerPlanRepository;
import com.example.demo.entity.CustumerPlan;
import io.swagger.annotations.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class AdminCustumerPlanService {

        private final CustumerPlanRepository custumerPlanRepository;

        @Autowired
        public AdminCustumerPlanService(CustumerPlanRepository custumerPlanRepository) {
            this.custumerPlanRepository = custumerPlanRepository;
        }

        // Method to retrieve all customer plans
        public List<CustumerPlan> getAllPlans() {
            return custumerPlanRepository.findAll();
        }

        // Method to retrieve a customer plan by its ID
        public Optional<CustumerPlan> getPlanById(Long id) {
            return custumerPlanRepository.findById(id);
        }

        // Method to save a customer plan
        public CustumerPlan savePlan(CustumerPlan plan) {
            return custumerPlanRepository.save(plan);
        }

        // Method to delete a customer plan by its ID
        public void deletePlanById(Long id) {
            custumerPlanRepository.deleteById(id);
        }

        public List<CustumerPlan> getAllRegistrtion() {
           return custumerPlanRepository.findAll();
        }

    }

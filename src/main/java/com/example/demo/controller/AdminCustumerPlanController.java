package com.example.demo.controller;

import com.example.demo.entity.CustumerPlan;
import com.example.demo.service.AdminCustumerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminCustumerPlanController {

    private final AdminCustumerPlanService AdminCustumerPlanService;

    @Autowired
    public AdminCustumerPlanController(com.example.demo.service.AdminCustumerPlanService AdminCustumerPlanService) {
        this.AdminCustumerPlanService = AdminCustumerPlanService;
    }

    // Mapping to display all customer plans

    @GetMapping("/getAllReg")
    public String getAllRegistrtion(Model model) {
        List<CustumerPlan> plan = AdminCustumerPlanService.getAllRegistrtion();
        model.addAttribute("plan", plan);
        return "Admin_get_ALL.jsp";
    }

    // Mapping to handle delete operation
    @GetMapping("/delete/{id}")
    public String deletePlan(@PathVariable("id") Long id, Model model) {
        AdminCustumerPlanService.deletePlanById(id);
        List<CustumerPlan> plan = AdminCustumerPlanService.getAllRegistrtion();
        model.addAttribute("plan", plan);
        return "Admin_get_ALL.jsp";
    }

    @GetMapping("/update/{id}")
//    public String updatePlan(@PathVariable("id") Long id, @ModelAttribute CustumerPlan updatedPlan) {
    public String updatePlan(@PathVariable("id") Long id, Model model) {

        Optional<CustumerPlan> plandetailsByid = AdminCustumerPlanService.getPlanById(id);

        // Check if the plan exists
        if (plandetailsByid.isPresent()) {
            // Plan found, add it to the model
            model.addAttribute("plan", plandetailsByid.get());
            return "update"; // Return the update form
        } else {
            // Plan not found, handle the error (e.g., redirect to an error page)
            return "error"; // Or return a different view
        }
    }

    @PostMapping("/customer/update")
    public String updateCustomer(Model model,
            @RequestParam("custumerPlanId") String custumerPlanId,
            @RequestParam("userId") String userId,
            @RequestParam("subscriptionActive") boolean subscriptionActive,
            @RequestParam("subscriptionActiveDate") String subscriptionActiveDate,
            @RequestParam("subscriptionExpirationDate") String subscriptionExpirationDate,
            @RequestParam("numberOfDays") int numberOfDays,
            @RequestParam("PlanName") String planName,
            @RequestParam("subscriptionPrice") double subscriptionPrice) {
        CustumerPlan custumerPlan=new CustumerPlan();
        custumerPlan.setPlanName(planName);
        custumerPlan.setCustumerPlanId(Long.valueOf(custumerPlanId));
        custumerPlan.setSubscriptionPrice((int) subscriptionPrice);
        custumerPlan.setUserId(userId);
        custumerPlan.setSubscriptionActive(subscriptionActive);
        custumerPlan.setSubscriptionExpirationDate(LocalDateTime.parse(subscriptionExpirationDate));
        custumerPlan.setSubscriptionActiveDate(LocalDateTime.parse(subscriptionActiveDate));
        custumerPlan.setNumberOfDays(numberOfDays);

        AdminCustumerPlanService.savePlan(custumerPlan);
        List<CustumerPlan> plan = AdminCustumerPlanService.getAllRegistrtion();
        model.addAttribute("plan", plan);
        return "Admin_get_ALL.jsp";
    }
}
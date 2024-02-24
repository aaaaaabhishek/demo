package com.example.demo.controller;import com.example.demo.service.CustumerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class CustumerPlanController {
    private final CustumerPlanService custumerPlanService;
    private final HttpServletRequest request;

    @Autowired
    public CustumerPlanController(CustumerPlanService custumerPlanService, HttpServletRequest request) {
        this.custumerPlanService = custumerPlanService;
        this.request = request;
    }

    @PostMapping("/subscribe-Standard")
    public String subscribeStandardCustomerPlan(@RequestParam("duration") String duration, @RequestParam("subscriptionPrice") String subscriptionPrice, HttpSession session) {
        String PlanName = "Standard";
        if (session != null) {
            String userId = (String) session.getAttribute("loginId");
            custumerPlanService.subscribeCustomerPlan(userId, Integer.parseInt(duration), PlanName, Integer.parseInt(subscriptionPrice));
        }
        return "Standard";
    }
    @PostMapping("/subscribe-Deluxe")
    public String subscribeDeluxeCustomerPlan(@RequestParam("duration") String duration, @RequestParam("subscriptionPrice") String subscriptionPrice, HttpSession session) {
        String PlanName = "Deluxe";
        if (session != null) {
            String userId = (String) session.getAttribute("loginId");
            custumerPlanService.subscribeCustomerPlan(userId, Integer.parseInt(duration), PlanName, Integer.parseInt(subscriptionPrice));
        }
        return "deluxe";
    }
    @PostMapping("/subscribe-Suite")
    public String subscribeSuiteCustomerPlan(@RequestParam("duration") String duration, @RequestParam("subscriptionPrice") String subscriptionPrice, HttpSession session) {
        String PlanName = "Suite";
        if (session != null) {
            String userId = (String) session.getAttribute("loginId");
            custumerPlanService.subscribeCustomerPlan(userId, Integer.parseInt(duration), PlanName, Integer.parseInt(subscriptionPrice));
        }
        return "suite";
    }
}

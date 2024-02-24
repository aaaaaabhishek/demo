package com.example.demo.Component;
import com.example.demo.Repositary.CustumerPlanRepository;
import com.example.demo.entity.CustumerPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Component
public class ExpirationScheduler{

    private final CustumerPlanRepository custumerPlanRepository;

    private final SessionRegistry sessionRegistry;

    @Autowired
    public ExpirationScheduler(CustumerPlanRepository custumerPlanRepository, SessionRegistry sessionRegistry) {
        this.custumerPlanRepository = custumerPlanRepository;
        this.sessionRegistry = sessionRegistry;
    }
    @Scheduled(fixedRate = 120000) // Scheduled to run every 2 minutes (120000 milliseconds)
    public void handleexpire() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (Object principal : allPrincipals) {
            List<SessionInformation> activeSessions = sessionRegistry.getAllSessions(principal, false);

            for (SessionInformation sessionInformation : activeSessions) {
                long inactiveTime = System.currentTimeMillis() - sessionInformation.getLastRequest().getTime();
                // Invalidate the session if it has been idle for more than 2 minutes
                if (inactiveTime > 120000) {
                    sessionInformation.expireNow();
                }
            }
        }
    }


    // Method to handle expiration logic
    //  @Scheduled(cron = "0 0 0 * * ?") // Run at midnight every day
    @Scheduled(cron = "0 56 10 * * ?") // Run at 1:27 PM every day
    public void handleExpiration() {
        LocalDateTime today = LocalDateTime.now();
        List<CustumerPlan> expiredPlans = custumerPlanRepository.findBySubscriptionActiveTrueAndSubscriptionExpirationDateBefore(today);
        for (CustumerPlan plan : expiredPlans) {
            plan.setSubscriptionActive(false);
            custumerPlanRepository.save(plan);

        }
    }}
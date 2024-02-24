package com.example.demo.service;
import com.example.demo.Repositary.CustumerPlanRepository;
import com.example.demo.entity.CustumerPlan;;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfGenerationService {

   @Autowired
   private com.example.demo.Repositary.CustumerPlanRepository CustumerPlanRepository;

    public byte[] generatePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            List<CustumerPlan> custumerPlans = CustumerPlanRepository.findAll();
            for (CustumerPlan custumerPlan : custumerPlans) {
                document.add(new Paragraph("Customer Plan ID: " + custumerPlan.getCustumerPlanId()));
                document.add(new Paragraph("User ID: " + custumerPlan.getUserId()));
                document.add(new Paragraph("Subscription Active: " + custumerPlan.getSubscriptionActive()));
                document.add(new Paragraph("Subscription Active Date: " + custumerPlan.getSubscriptionActiveDate()));
                document.add(new Paragraph("Subscription Expiration Date: " + custumerPlan.getSubscriptionExpirationDate()));
                document.add(new Paragraph("Number Of Days: " + custumerPlan.getNumberOfDays()));
                document.add(new Paragraph("Plan Name: " + custumerPlan.getPlanName()));
                document.add(new Paragraph("Subscription Price: " + custumerPlan.getSubscriptionPrice()));
                document.add(new Paragraph("\n"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}

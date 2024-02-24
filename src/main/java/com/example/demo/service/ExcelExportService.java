package com.example.demo.service;

import com.example.demo.Repositary.CustumerPlanRepository;
import com.example.demo.entity.CustumerPlan;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class ExcelExportService {
    @Autowired
    private CustumerPlanRepository custumerPlanRepository;
    public byte[] exportUserToExcel() throws IOException {
        List<CustumerPlan> custumerPlans = custumerPlanRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Customer Plan ID", "User ID", "Subscription Active", "Subscription Active Date", "Subscription Expiration Date", "Number of Days","Plan Name","Subscription Price"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }
            int rowNum = 1;
            // Populate data rows
            for (CustumerPlan custumerPlan : custumerPlans) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(custumerPlan.getCustumerPlanId());
                row.createCell(1).setCellValue(custumerPlan.getUserId());
                row.createCell(2).setCellValue(custumerPlan.getSubscriptionActive());
                row.createCell(3).setCellValue(custumerPlan.getSubscriptionActiveDate().toString());
                row.createCell(4).setCellValue(custumerPlan.getSubscriptionExpirationDate().toString());
                row.createCell(5).setCellValue(custumerPlan.getNumberOfDays());
                row.createCell(6).setCellValue(custumerPlan.getPlanName());
                row.createCell(7).setCellValue(custumerPlan.getSubscriptionPrice());
            }
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}


//        // Create header row
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("Customer Plan ID");
//        headerRow.createCell(1).setCellValue("User ID");
//        headerRow.createCell(2).setCellValue("Subscription Active");
//        headerRow.createCell(3).setCellValue("Subscription Active Date");
//        headerRow.createCell(4).setCellValue("Subscription Expiration Date");
//        headerRow.createCell(5).setCellValue("Number of Days");
//        headerRow.createCell(6).setCellValue("Plan Name");
//        headerRow.createCell(7).setCellValue("Subscription Price");
//
//        // Populate data rows
//        int rowNum = 1;
//        for (CustumerPlan custumerPlan : custumerPlans) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(custumerPlan.getCustumerPlanId());
//            row.createCell(1).setCellValue(custumerPlan.getUserId());
//            row.createCell(2).setCellValue(custumerPlan.getSubscriptionActive());
//            row.createCell(3).setCellValue(custumerPlan.getSubscriptionActiveDate().toString());
//            row.createCell(4).setCellValue(custumerPlan.getSubscriptionExpirationDate().toString());
//            row.createCell(5).setCellValue(custumerPlan.getNumberOfDays());
//            row.createCell(6).setCellValue(custumerPlan.getPlanName());
//            row.createCell(7).setCellValue(custumerPlan.getSubscriptionPrice());
//        }
//
//        // Write the workbook to a file
//        try (FileOutputStream outputStream = new FileOutputStream("CustomerPlans.xlsx")) {
//            workbook.write(outputStream);
//        }
//        return null;
//    }
//}

package com.example.demo.controller;
import com.example.demo.service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private PdfGenerationService pdfGenerationService;
    @GetMapping("/generate")
    public ResponseEntity<byte[]> generatePdf() {
        byte[] pdfContents = pdfGenerationService.generatePdf();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "users.pdf");
        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }
}
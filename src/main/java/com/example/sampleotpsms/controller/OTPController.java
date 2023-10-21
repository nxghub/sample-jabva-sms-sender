package com.example.sampleotpsms.controller;

import com.example.sampleotpsms.entity.OTP;
import com.example.sampleotpsms.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    private final OTPService otpService;

    @Autowired
    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateOTP(@RequestParam String email, @RequestParam String phoneNumber) {
        OTP otpEntity = otpService.generateOTP(email, phoneNumber);
        return new ResponseEntity<>("OTP generated and sent to " + phoneNumber, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOTP(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.validateOTP(email, otp);
        if (isValid) {
            return new ResponseEntity<>("OTP is valid.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP.", HttpStatus.BAD_REQUEST);
        }
    }
}

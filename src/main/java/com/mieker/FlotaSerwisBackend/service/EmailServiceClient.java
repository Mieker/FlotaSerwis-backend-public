package com.mieker.FlotaSerwisBackend.service;

import com.mieker.FlotaSerwisBackend.model.dto.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "emailService", url = "https://flotaserwis-backend-email.herokuapp.com/api")
//@RequestMapping("/api")
public interface EmailServiceClient {

    @PostMapping("/email")
    ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto);
}

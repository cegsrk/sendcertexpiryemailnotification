package com.ich.portal.common.job.sendcertexpiryemailnotification.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
@RestController
@RequestMapping(NotificationController.BASE_URL)
public class NotificationController {
    public static final String BASE_URL = "/notificationcontroller.svc/api/v1";
    @PostMapping("/sendExpiringCertificates")
    public String sendExpiringCertificates() {
        // Implement
        System.out.println("Connection to HANA successful!");
        return "completed";
    }
}

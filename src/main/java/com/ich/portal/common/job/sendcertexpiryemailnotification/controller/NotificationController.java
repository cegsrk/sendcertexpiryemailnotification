package com.ich.portal.common.job.sendcertexpiryemailnotification.controller;

import com.ich.portal.common.job.sendcertexpiryemailnotification.util.*;

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

import static com.ich.portal.common.job.sendcertexpiryemailnotification.MessageKeys.SEND_INVITATION_EMAIL_ERROR;

@RestController
@RequestMapping(NotificationController.BASE_URL)
public class NotificationController {
    public static final String BASE_URL = "/notificationcontroller.svc/api/v1";
    private static Connection connectionCommon;
    private static Connection connectionUser;
    private static ResultSet resultSet;
    private static Statement statementCommon;
    private static Statement statementUser;

    @PostMapping("/sendExpiringCertificates")
    public String sendExpiringCertificates(@Value("${hana.por-db-common.url}")final String HANA_URL_COMMON, @Value("${hana.por-db-common.user}")final String HANA_USER_COMMON, @Value("${hana.por-db-common.password}")final String HANA_PASSWORD_COMMON, @Value("${hana.por-db-user-mgmt.url}")final String HANA_URL_USERMGMT, @Value("${hana.por-db-user-mgmt.user}")final String HANA_USER_USERMGMT, @Value("${hana.por-db-user-mgmt.password}")final String HANA_PASSWORD_USERMGMT) {
        // Implement
        connectionCommon = ConnectToHANADatabase.connectToHANADatabase(HANA_URL_COMMON, HANA_USER_COMMON, HANA_PASSWORD_COMMON);
        connectionUser = ConnectToHANADatabase.connectToHANADatabase(HANA_URL_USERMGMT, HANA_USER_USERMGMT, HANA_PASSWORD_USERMGMT);
        if (connectionCommon != null && connectionUser != null) {
            try {
                System.out.println("Connection to HANA successful!");
                statementCommon = connectionCommon.createStatement();
                statementUser = connectionUser.createStatement();
                resultSet = statementCommon.executeQuery("SELECT INVITATIONID,INVITERCODE,INVITEECODE,INVITEREMAIL,INVITEEEMAIL,INVITERUSERNAME,INVITEEUSERNAME,INVITERCOMPANYNAME,VERSION,ADDITIONALMESSAGE from ICH_INVITATION_MANAGEMENT_INVITATION WHERE INVITATIONSYNCED=false;");

            }catch (SQLException e) {
                System.err.println("Query failed!");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SEND_INVITATION_EMAIL_ERROR);
            }
        }

        return "completed";
    }
}

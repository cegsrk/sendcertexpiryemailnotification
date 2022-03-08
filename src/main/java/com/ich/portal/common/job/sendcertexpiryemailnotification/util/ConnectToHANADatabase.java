package com.ich.portal.common.job.sendcertexpiryemailnotification.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.ich.portal.common.job.sendcertexpiryemailnotification.MessageKeys.HANA_DB_CONNECTION_ERROR;

public class ConnectToHANADatabase {

    public static Connection connectToHANADatabase(String HANA_URL, String HANA_USER, String HANA_PASSWORD){
        Connection connection;
        Properties info = new Properties();
        try {
            //info.put("databaseName", HANA_DATABASENAME);
            info.put("user", HANA_USER);
            info.put("password", HANA_PASSWORD);
            connection = DriverManager.getConnection(HANA_URL, info);
        }
        catch (SQLException e) {
            System.err.println("Connection to HANA Failed:" + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, HANA_DB_CONNECTION_ERROR);
        }
        return connection;
    }
}

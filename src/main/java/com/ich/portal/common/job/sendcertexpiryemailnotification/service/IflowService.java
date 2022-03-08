package com.ich.portal.common.job.sendcertexpiryemailnotification.service;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IflowService {
    private static final String IFLOW_DESTINATION = "CPI-Tenant-Iflow-API";
    private static HttpClient iflowClient;
    private static HttpDestination iflowDestination;

    public static int sendInvitationAndOtpEmail(String invitationPayload) throws IOException {

        iflowDestination = DestinationAccessor.getDestination(IFLOW_DESTINATION).asHttp();
        iflowClient = HttpClientAccessor.getHttpClient(iflowDestination);

        HttpPost post = new HttpPost(iflowDestination.getUri() + "/preparebpinvitationemail");
        post.setEntity(new StringEntity(invitationPayload));
        HttpResponse response = iflowClient.execute(post);
        System.out.println(response);

        return response.getStatusLine().getStatusCode();

    }
}

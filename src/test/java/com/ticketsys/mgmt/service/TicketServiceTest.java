package com.ticketsys.mgmt.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ws.transport.TransportConstants;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Test
    public void test() {
        String msg = "saveTicket.requestDto.ticket: ticket size minimum=3 and maximum=500, saveTicket.requestDto.description: description size minimum=3 and maximum=1500, saveTicket.requestDto.ticket: must not be blank";
        System.out.println(
                Arrays.asList(msg.split("\\,")).stream()
                        .map(kvMsg -> kvMsg.split("\\:").length > 1
                        ? kvMsg.split("\\:")[1]
                        : kvMsg.split("\\:")[0])
                        .collect(Collectors.toList()));
        List<Object> msgList = Arrays.asList(
                msg.split("\\,")).stream()
                .map(kvMsg -> kvMsg.split("\\:").length > 1
                        ? kvMsg.split("\\:")[1]
                        : kvMsg.split("\\:")[0])
                .collect(Collectors.toList());
        System.out.println(msgList);
    }

    @Test
    public void jiraTest() {
        String url = "https://mdoss.atlassian.net";
        try {
//                   GetRequest gReq =  Unirest.get("https://mdoss.atlassian.net")//Unirest.get("https://mdoss.atlassian.net")//Unirest.get("/rest/api/3/atlassian-addons-project-access")//Unirest.get("/rest/api/3/applicationrole")
//                    //                .basicAuth("mdoss@altimetrik.com", "<api_token>")
//                    .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")//"3qlF3XZUQfD2kLANu7nA")
//                    .header("Accept", "application/json");
//            JSONObject obj = new JSONObject(new JSONTokener(gReq.asJson().getRawBody()));
//            System.out.println(obj);
            Map<String, String> headers = new HashMap<>();
            headers.put(TransportConstants.HEADER_CONTENT_TYPE, "application/json");
            headers.put(TransportConstants.HEADER_ACCEPT, "application/json");
            headers.put(TransportConstants.HEADER_ACCEPT_ENCODING, "UTF-8");
            HttpResponse<JsonNode> response =  Unirest.get("https://mdoss.atlassian.net")//Unirest.get("https://mdoss.atlassian.net")//Unirest.get("/rest/api/3/atlassian-addons-project-access")//Unirest.get("/rest/api/3/applicationrole")
                    //                .basicAuth("mdoss@altimetrik.com", "<api_token>")
                    .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")//"3qlF3XZUQfD2kLANu7nA")
                    .headers(headers).asJson();
//                    .header(TransportConstants.HEADER_CONTENT_TYPE, "application/json")
//                    .header("Accept", "application/json").asJson();
//            JSONObject obj = new JSONObject(new JSONTokener(gReq.asJson().getRawBody()));
            System.out.println(response.getBody());
        }catch (Exception ex) {
            ex.printStackTrace();
        }

//        HttpResponse<String> response = Unirest
//                .post(url)
//                .header(HEADER_CONTENT_TYPE, HEADER_VALUE_APPLICATON_JSON)
//                .body(payload)
//                .asString();
    }
}

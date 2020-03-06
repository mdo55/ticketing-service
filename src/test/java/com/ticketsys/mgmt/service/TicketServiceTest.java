package com.ticketsys.mgmt.service;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ws.transport.TransportConstants;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Ignore
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
            Map<String, String> headers = getHeaders();
            HttpResponse<JsonNode> response =  Unirest.get("https://mdoss.atlassian.net/rest/api/3/applicationrole")//Unirest.get("https://mdoss.atlassian.net")//Unirest.get("/rest/api/3/atlassian-addons-project-access")//Unirest.get("/rest/api/3/applicationrole")
                    //                .basicAuth("mdoss@altimetrik.com", "<api_token>")
                    .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")//"3qlF3XZUQfD2kLANu7nA")
                    .headers(headers).asJson();
            //"https://mdoss.atlassian.net/rest/api/3/issue"
//                    .header(TransportConstants.HEADER_CONTENT_TYPE, "application/json")
//                    .header("Accept", "application/json").asJson();
//            JSONObject obj = new JSONObject(new JSONTokener(gReq.asJson().getRawBody()));
//            System.out.println(response.getBody());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(TransportConstants.HEADER_CONTENT_TYPE, "application/json");
        headers.put(TransportConstants.HEADER_ACCEPT, "application/json");
        headers.put(TransportConstants.HEADER_ACCEPT_ENCODING, "UTF-8");
        return headers;
    }
    @Ignore
    @Test
    public void createJiraIssu() {
        try {
            // Connect Jackson ObjectMapper to Unirest
            Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
                private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                        = new com.fasterxml.jackson.databind.ObjectMapper();

                public <T> T readValue(String value, Class<T> valueType) {
                    try {
                        return jacksonObjectMapper.readValue(value, valueType);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                public String writeValue(Object value) {
                    try {
                        return jacksonObjectMapper.writeValueAsString(value);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            HttpResponse<JsonNode> response = Unirest.post("https://mdoss.atlassian.net/rest/api/3/issue")
                    .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(payload())
                    .asJson();
            System.out.println(response.getBody());
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
//            mapper.
//            HttpResponse<JsonNode> responses = Unirest.get("https://mdoss.atlassian.net/rest/api/3/issue/TIC-1")
//                    .basicAuth("mdoss@altimetrik.com", "Y7Qf6JgQhqfoe6XYCmY3D256")//"3qlF3XZUQfD2kLANu7nA")
//                    .headers(getHeaders()).asJson();
//            System.out.println(responses.getBody());
//            BasicIssue bIssue = mapper.readValue(response.getRawBody(), TestClass.class);
//            System.out.println(bIssue);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private ObjectNode payload() {
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode update = payload.putObject("update");
            {
            }
            ObjectNode fields = payload.putObject("fields");
            {
                fields.put("summary", "Render Issue");
//                ObjectNode parent = fields.putObject("parent");
//                {
//                    parent.put("key", "TIC");
//                }
                ObjectNode issuetype = fields.putObject("issuetype");
                {
                    issuetype.put("id", "10005");
                }
//                ArrayNode components = fields.putArray("components");
//                ObjectNode components0 = components.addObject();
//                {
//                    components0.put("id", "10000");
//                }
//                fields.put("customfield_20000", "06/Jul/19 3:25 PM");
//                ObjectNode customfield_40000 = fields.putObject("customfield_40000");
//                {
//                    customfield_40000.put("type", "doc");
//                    customfield_40000.put("version", 1);
//                    ArrayNode content = customfield_40000.putArray("content");
//                    ObjectNode content0 = content.addObject();
//                    {
//                        content0.put("type", "paragraph");
//                        ArrayNode content1 = content0.putArray("content");
//                        ObjectNode content2 = content1.addObject();
//                        {
//                            content2.put("text", "Occurs on all orders");
//                            content2.put("type", "text");
//                        }
//                    }
//                }
//                ArrayNode customfield_70000 = fields.putArray("customfield_70000");
//                customfield_70000.add("jira-administrators");
//                customfield_70000.add("jira-software-users");
                ObjectNode project = fields.putObject("project");
                {
                    project.put("id", "10000");
                }
                ObjectNode description = fields.putObject("description");
                {
                    description.put("type", "doc");
                    description.put("version", 1);
                    ArrayNode content1 = description.putArray("content");
                    ObjectNode content0 = content1.addObject();
                    {
                        content0.put("type", "paragraph");
                        ArrayNode content2 = content0.putArray("content");
                        ObjectNode content3 = content2.addObject();
                        {
                            content3.put("text", "After submiting the next page not rendered properly.");
                            content3.put("type", "text");
                        }
                    }
                }
                ArrayNode attachments = fields.putArray("attachment");
                ObjectNode attachment = attachments.addObject();
                {
                    attachment.put("type", "array");
                }
//                ObjectNode reporter = fields.putObject("reporter");
//                {
//                    reporter.put("emailAddress", "mdoss@altimetrik.com");
//                    reporter.put("accountId", "5e5613253df51b0c93756bf1");
//                }
//                ArrayNode fixVersions = fields.putArray("fixVersions");
//                ObjectNode fixVersions0 = fixVersions.addObject();
//                {
//                    fixVersions0.put("id", "10001");
//                }
//                fields.put("customfield_10000", "09/Jun/19");
                ObjectNode priority = fields.putObject("priority");
                {
                    priority.put("id", "5");
                }
                ArrayNode labels = fields.putArray("labels");
                labels.add("bugfix");
                labels.add("blitz_test");
                ObjectNode timetracking = fields.putObject("timetracking");
                {
                    timetracking.put("remainingEstimate", "5");
                    timetracking.put("originalEstimate", "10");
                }
//                ArrayNode customfield_30000 = fields.putArray("customfield_30000");
//                customfield_30000.add("10000");
//                customfield_30000.add("10002");
//                ObjectNode customfield_80000 = fields.putObject("customfield_80000");
//                {
//                    customfield_80000.put("value", "red");
//                }
//                ObjectNode security = fields.putObject("security");
//                {
//                    security.put("id", "10000");
//                }
                ObjectNode environment = fields.putObject("environment");
                {
                    environment.put("type", "doc");
                    environment.put("version", 1);
                    ArrayNode content1 = environment.putArray("content");
                    ObjectNode content0 = content1.addObject();
                    {
                        content0.put("type", "paragraph");
                        ArrayNode content = content0.putArray("content");
                        ObjectNode content2 = content.addObject();
                        {
                            content2.put("text", "UAT");
                            content2.put("type", "text");
                        }
                    }
                }
                ArrayNode versions = fields.putArray("versions");
                ObjectNode versions0 = versions.addObject();
                {
                    versions0.put("id", "1001.0.0-SNAPSHOT");
                }
                fields.put("duedate", "2020-05-11T00:00:00.000Z");
//                fields.put("customfield_60000", "jira-software-users");
//                ObjectNode customfield_50000 = fields.putObject("customfield_50000");
//                {
//                    customfield_50000.put("type", "doc");
//                    customfield_50000.put("version", 1);
//                    ArrayNode content1 = customfield_50000.putArray("content");
//                    ObjectNode content0 = content1.addObject();
//                    {
//                        content0.put("type", "paragraph");
//                        ArrayNode content = content0.putArray("content");
//                        ObjectNode content2 = content.addObject();
//                        {
//                            content2.put("text", "Could impact day-to-day work.");
//                            content2.put("type", "text");
//                        }
//                    }
//                }
//                ObjectNode assignee = fields.putObject("assignee");
//                {
//                    assignee.put("id", "5b109f2e9729b51b54dc274d");
//                }
            }
        }

        return payload;
    }
}

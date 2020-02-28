package com.ticketsys.mgmt.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
//import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.util.JsonLoader;
import com.google.common.collect.Lists;
import com.ticketsys.mgmt.dto.request.TicketInfoRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSchemaManager {
    private final JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
    private Map<Class<?>, JsonNode> jsonNodeMap = new HashMap<>();
    public void load(Class<?> className, String schema) throws IOException {
        System.out.println("schema-------"+schema);
        JsonNode schemaFromDisk = JsonLoader.fromURL(this.getClass().getResource(schema));
        System.out.println(schemaFromDisk);
        JsonNode ticketReq = schemaFromDisk.get("definitions").get("TicketInfoRequest");
//        System.out.println(ticketReq);
        jsonNodeMap.put(className, ticketReq);
    }

    public void check(Class<?> className, JsonNode toBeValidate) {
        ProcessingReport report = null;
        try {
            System.out.println(jsonNodeMap);
            report = validator.validate(jsonNodeMap.get(className), toBeValidate);
            if (!report.isSuccess()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" Oops!! failed JSON validation ");
                stringBuilder.append(":").append("\n");
                List messages = Lists.newArrayList(report);
                for (int i = 0; i < messages.size(); i++) {
                    stringBuilder.append("- ");
                    stringBuilder.append(messages.get(i).toString());
                    stringBuilder.append((i == (messages.size()) - 1) ? "" : "\r");
                }
                throw new RuntimeException(stringBuilder.toString());
            }
        } catch (ProcessingException e) {
            throw new RuntimeJsonMappingException("ERROR -->" + e.toString());
        }
    }
}

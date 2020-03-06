package com.ticketsys.mgmt.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author mdoss.
 */
@ConfigurationProperties
@Configuration
@Data
public class Config {
    @Value("${jira.user}")
    private String jiraUser;
    @Value("${jira.api-token}")
    private String jiraApiToken;
    @Value("${jira.issue-url}")
    private String issueUrl;
    @Value("${jira.domain-url}")
    private String domainUrl;
    @Value("${jira.priority-url}")
    private String priorityUrl;
    @Value("${jira.issue-type-url}")
    private String issueTypeUrl;

}

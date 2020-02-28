package com.ticketsys.mgmt.config;

//import com.atlassian.oai.validator.OpenApiInteractionValidator;
//import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
//import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.io.IOException;

//@Configuration
public class OpenApiValidationConfig { //implements WebMvcConfigurer {
//    private static final long serialVersionUID = 1;
//    private final OpenApiValidationInterceptor validationInterceptor;
//    @Autowired
//    public OpenApiValidationConfig() throws IOException {
//        this.validationInterceptor = new OpenApiValidationInterceptor(OpenApiInteractionValidator
//                .createFor("classpath:oa3/openapi.yml").build());
//    }
//    public Filter  validationFilter() {
//        return new OpenApiValidationFilter(
//                true, // enable request validation
//                false // enable response validation
//        );
//    }
//    @Override
//    public void addInterceptors(final InterceptorRegistry registry) {
//        registry.addInterceptor(validationInterceptor);
//    }
}

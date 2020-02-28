package com.ticketsys.mgmt.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ServiceUtil {
    /**
     * segregate constraintViolation exception.
     * @param kvPairMessage
     * @return object
     */
    public static Object segregateMessage(String kvPairMessage) {
        if (!StringUtils.isEmpty(kvPairMessage)) {
            return Arrays.asList(
                    kvPairMessage.split("\\,")).stream()
                    .map(kvMsg -> kvMsg.split("\\:").length > 1
                            ? kvMsg.split("\\:")[1]
                            : kvMsg.split("\\:")[0])
                    .collect(Collectors.joining(","));
        }else {
            return new Object();
        }
    }
}

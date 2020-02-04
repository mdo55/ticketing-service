package com.ticketsys.mgmt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StackTrace {
    private String errorCode;
    private String errorMessage;
}

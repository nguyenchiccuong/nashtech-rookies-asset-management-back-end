package com.nashtech.rootkies.dto.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ResponseDTO {
    private String errorCode;
    private Object data;
    private String successCode;
}

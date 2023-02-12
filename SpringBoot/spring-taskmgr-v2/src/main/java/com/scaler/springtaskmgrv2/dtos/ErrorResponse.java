package com.scaler.springtaskmgrv2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorResponse {

    @Getter
    @Setter
    private String message;

}

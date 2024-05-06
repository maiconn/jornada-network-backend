package com.jornada.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
    private Date timestamp;
    private int statusCode;
    private String message;
}

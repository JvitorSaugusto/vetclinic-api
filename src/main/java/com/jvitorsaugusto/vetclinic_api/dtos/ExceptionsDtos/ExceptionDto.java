package com.jvitorsaugusto.vetclinic_api.dtos.ExceptionsDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDto {

    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
}

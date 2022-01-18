package com.evgensoft.dto.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class StatusResponse {
    private boolean success;
    private List<String> errors;

    public StatusResponse(boolean success) {
        this.success = success;
    }

    public StatusResponse(boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public static StatusResponse getOk() {
        return new StatusResponse(true);
    }

    public static StatusResponse getBad(List<String> errors) {
        return new StatusResponse(false, errors);
    }
}
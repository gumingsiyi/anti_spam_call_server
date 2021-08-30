package com.gu.antiSpamCall.dto.response;

import lombok.Data;

@Data
public class CallInfoResponse {
    long callCount;
    long antiCount;
}

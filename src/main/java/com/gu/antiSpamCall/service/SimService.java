package com.gu.antiSpamCall.service;

import java.util.List;

public interface SimService {
    String simCall(String from, String to);
    void clearCallRecord();
    List<String> getBlackList(String num);
    List<String> getWhiteList(String num);
}

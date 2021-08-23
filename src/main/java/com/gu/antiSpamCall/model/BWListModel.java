package com.gu.antiSpamCall.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "userBWList")
@Data
public class BWListModel {
    String mobNum;
    List<String> blackList;
    List<String> whiteList;
}

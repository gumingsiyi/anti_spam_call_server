package com.gu.antiSpamCall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "callRecord")
@Data
@AllArgsConstructor
public class CallRecord {
    @Id
    String _id;

    String from;    //主叫
    String to;      //被叫
    String time;    //拨打时间
    long count;     //当天拨打次数
}

package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarbonInfo {

    private int infoNo;
    private String infoTitle;
    private java.sql.Date infoDate;
    private int infoView;
    private String infoContent;
    private String infoSource;
    private int adminNo;

}

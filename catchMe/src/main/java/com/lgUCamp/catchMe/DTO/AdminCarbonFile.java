package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminCarbonFile {

    private int infoNo;
    private String infoTitle;
    private java.sql.Date infoDate;
    private int infoView;
    private String infoContent;
    private String infoSource;
    private int adminNo;

    private AdminDTO admin;

    private CarbonInfoFileDTO carbonInfoFileList;


}

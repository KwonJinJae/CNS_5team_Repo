package com.lgUCamp.catchMe.DTO;

import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarbonInfoDTO {

    private int info_no;
    private String info_title;
    private java.sql.Date info_date;
    private int info_view;


    private String info_content;
    private int admin_no;
}

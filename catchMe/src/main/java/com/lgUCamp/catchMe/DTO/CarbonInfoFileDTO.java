package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarbonInfoFileDTO {

    private int infoNo;
    private String infoFileName;
    private String infoFilePath;

}

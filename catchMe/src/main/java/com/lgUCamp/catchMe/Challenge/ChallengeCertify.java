package com.lgUCamp.catchMe.Challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ChallengeCertify {
    int cProofNo;
    Date cProofDate;
    String cProofTitle;
    String cProofContent;
    int cProofViews;
    int cNoticeNo;
    String cProofImg;
    int userNo;

}
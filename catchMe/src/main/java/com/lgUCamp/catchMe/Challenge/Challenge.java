package com.lgUCamp.catchMe.Challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Challenge {
    int cNoticeNo;
    Date cNoticeDate;
    Date cNoticeStart;
    Date cNoticeEnd;
    String cNoticeTarget;
    int cNoticeViews;
    String cNoticeTitle;
    String cNoticeContent;
    String cNoticeImg;
    String cNoticeImgPath;
    String cNoticeFile;
    String cNoticeFilePath;
    int adminNo;

//    public Challenge(String cNoticeImg,String cNoticeTitle){
//        this.cNoticeImg = cNoticeImg;
//        this.cNoticeTitle = cNoticeTitle;
//
//    }

}

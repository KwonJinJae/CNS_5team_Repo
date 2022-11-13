package com.lgUCamp.catchMe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class VideoDTO {
    /*
VIDEO_NO
VIDEO_TITLE
VIDEO_URL
VIDEO_CONTENT
VIDEO_DATE
VIDEO_HITS
VIDEO_IMG
VIDEO_IMG_PATH
ADMIN_NO
    */
    private int videoNo;
    private String videoTitle;
    private String videoUrl;
    private String videoContent;
    private Date videoDate;
    private int videoHits;
    private String videoImg;
    private String videoImgPath;
    private int adminNo;


    public VideoDTO(String videoTitle, String videoUrl, String videoContent, String videoImg) {
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.videoContent = videoContent;
        this.videoImg = videoImg;
    }

    public VideoDTO(int videoNo, String videoTitle, String videoUrl, String videoContent, String videoImg) {
        this.videoNo = videoNo;
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.videoContent = videoContent;
        this.videoImg = videoImg;
    }
}

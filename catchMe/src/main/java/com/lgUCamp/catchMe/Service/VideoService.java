package com.lgUCamp.catchMe.Service;


import com.lgUCamp.catchMe.DTO.VideoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface VideoService {
    ArrayList<VideoDTO> videolist();

    VideoDTO oneVideolist(int video_no);
    ArrayList<VideoDTO> Keyword_title_Videolist(String keyword);

    ArrayList<VideoDTO> Keyword_content_Videolist(String keyword);

    ArrayList<VideoDTO> Keyword_all_Videolist(String keyword);

    void insertVideo(VideoDTO videoDTO);

    void updateVideo(VideoDTO videoDTO);

    void deleteVideo(int video_no);



}

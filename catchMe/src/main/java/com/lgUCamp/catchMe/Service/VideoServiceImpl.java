package com.lgUCamp.catchMe.Service;


import com.lgUCamp.catchMe.DTO.VideoDTO;
import com.lgUCamp.catchMe.Mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public ArrayList<VideoDTO> videolist() {
        System.out.println("videolist: "+videoMapper.videolist());
        return videoMapper.videolist();
    }

    @Override
    public VideoDTO oneVideolist(int video_no) {
        return videoMapper.oneVideolist(video_no);
    }

    @Override
    public ArrayList<VideoDTO> Keyword_title_Videolist(String keyword) {
        return videoMapper.Keyword_title_Videolist(keyword);
    }

    @Override
    public ArrayList<VideoDTO> Keyword_content_Videolist(String keyword) {
        return videoMapper.Keyword_content_Videolist(keyword);
    }

    @Override
    public ArrayList<VideoDTO> Keyword_all_Videolist(String keyword) {
        return videoMapper.Keyword_all_Videolist(keyword);
    }

    @Override
    public void insertVideo(VideoDTO videoDTO) {
        videoMapper.insertVideo(videoDTO);
    }

    @Override
    public void updateVideo(VideoDTO videoDTO) {
        videoMapper.updateVideo(videoDTO);
    }

    @Override
    public void deleteVideo(int video_no) {
        videoMapper.deleteVideo(video_no);
    }



}

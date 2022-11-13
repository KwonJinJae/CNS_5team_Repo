package com.lgUCamp.catchMe.Mapper;


import com.lgUCamp.catchMe.DTO.VideoDTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface VideoMapper {


    ArrayList<VideoDTO> Keyword_title_Videolist(String keyword);
    ArrayList<VideoDTO> Keyword_content_Videolist(String keyword);
    ArrayList<VideoDTO> Keyword_all_Videolist(String keyword);


    ArrayList<VideoDTO> videolist();
    @Select("select * from tbl_video where video_no = #{videoNo}")
    VideoDTO oneVideolist(int video_no);

    /*Insert into TBL_VIDEO values (VIDEO_NO_SEQ.NEXTVAL,' 2021 환경재단 활동영상','https://www.youtube.com/watch?v=KtMUdMIbY5I','환경재단은 올 한 해도 정부, 기업, 시민사회와 손잡고 기후환경니다.',to_date('2021/12/03','YYYY/MM/DD'),0,'VIMG1.PNG','img/',1);*/
    @Insert("insert into tbl_video values(VIDEO_NO_SEQ.NEXTVAL, #{videoTitle},#{videoUrl},#{videoContent},sysdate,0,#{videoImg},'img/',1)")
    void insertVideo(VideoDTO videoDTO);

    @Update("update tbl_video set video_title=#{videoTitle},video_url=#{videoUrl}, video_content=#{videoContent},video_img=#{videoImg} where video_no = #{videoNo} ")
    void updateVideo(VideoDTO videoDTO);

    @Delete("delete tbl_video where video_no=#{videoNo}")
    void deleteVideo(int video_no);



}

package com.lgUCamp.catchMe.Challenge;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ChallengeMapper {
    @Select("select * from TBL_CHALLENGE_NOTICE")
   public ArrayList<Challenge> selectAll();

    @Select("select * from TBL_CHALLENGE_NOTICE where C_NOTICE_NO=#{cNoticeNo}")
   public Challenge selectOne(int cNoticeNo);


//    select * from TBL_CHALLENGE_NOTICE where C_NOTICE_NO='21'





}

package com.lgUCamp.catchMe.Challenge;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ChallengeMapper {
    @Select("select * from TBL_CHALLENGE_NOTICE Order By C_NOTICE_DATE Desc")
   public ArrayList<Challenge> selectNoticeAll();


    //@Select("select * from TBL_CHALLENGE_PROOF Order By C_PROOF_DATE Desc")
    @Select("select * from TBL_CHALLENGE_PROOF")
    public ArrayList<ChallengeCertify> selectCertifyAll();

    @Select("select * from TBL_CHALLENGE_NOTICE where rownum <=3 Order By C_NOTICE_DATE Desc")
    public ArrayList<Challenge> selectNoticeThree();

    @Select("select * from TBL_CHALLENGE_NOTICE where C_NOTICE_NO=#{cNoticeNo}")
   public Challenge selectOne(int cNoticeNo);

    @Select("select * from TBL_CHALLENGE_PROOF where C_PROOF_NO=#{cProofNo}")
    public ChallengeCertify selectCertifyOne(int cProofNo);



    void insertNotice(Challenge challenge);
    //@Insert("insert into TBL_CHALLENGE_PROOF(C_PROOF_NO,C_PROOF_DATE,C_PROOF_TITLE,C_PROOF_CONTENT,C_PROOF_VIEWS,C_NOTICE_NO,C_PROOF_IMG,USER_NO) values (#{cProofNo, jdbcType=NUMERIC}, #{cProofDate, jdbcType=NUMERIC}, #{cProofTitle, jdbcType=VARCHAR}, #{cProofContent, jdbcType=CLOB}, #{cProofViews, jdbcType=NUMERIC},#{cNoticeNo , jdbcType=NUMERIC},#{cProofImg, jdbcType=VARCHAR},#{userNo, jdbcType=NUMERIC}")
    void insertCertify(ChallengeCertify challengeCertify);

    void deleteNotice(int cNoticeNo);
    void deleteCertify(int cProofNo);


//    select * from TBL_CHALLENGE_NOTICE where C_NOTICE_NO='21'





}

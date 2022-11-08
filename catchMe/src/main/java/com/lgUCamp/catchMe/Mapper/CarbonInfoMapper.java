package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.DTO.CarbonInfo;
import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CarbonInfoMapper {

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    ArrayList<CarbonInfo> carbonInfoList();

    /* 탄소중립 상세 게시글 조회 */
    AdminCarbonFile carbonInfoDetail(int infoNo);
}

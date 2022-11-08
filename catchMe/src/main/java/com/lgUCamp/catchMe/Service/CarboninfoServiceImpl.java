package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import com.lgUCamp.catchMe.DTO.CarbonInfo;

import com.lgUCamp.catchMe.Mapper.CarbonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarboninfoServiceImpl implements CarbonInfoService{

    @Autowired
    CarbonInfoMapper carbonInfoMapper;

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    public ArrayList<CarbonInfo> carbonInfoList(){

        return carbonInfoMapper.carbonInfoList();
    }

    /* 탄소중립 상세 게시글 조회 */
    @Override
    public AdminCarbonFile carbonInfoDetail(int infoNo) {

        return carbonInfoMapper.carbonInfoDetail(infoNo);

    }
}

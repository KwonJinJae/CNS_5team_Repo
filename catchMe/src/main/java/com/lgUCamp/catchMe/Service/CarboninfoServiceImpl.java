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

    /* 탄소중립 신규 게시글 등록 */
    @Override
    public void carbonInfoInsert(CarbonInfo carbonInfo) {

        carbonInfoMapper.carbonInfoInsert(carbonInfo);
    }

    /* 탄소중립 수정을 위한 조회 */
    @Override
    public AdminCarbonFile carbonInfoModifySelect(int infoNo) {

        return carbonInfoMapper.carbonInfoModifySelect(infoNo);
    }

    /* 탄소 중립 게시글 수정 */
    @Override
    public void carbonInfoModify(CarbonInfo carbonInfo) {

        carbonInfoMapper.carbonInfoModify(carbonInfo);
    }

    /* 탄소 중립 게시글 삭제 */
    @Override
    public int carbonInfoDelete(int infoNo) {

        return carbonInfoMapper.carbonInfoDelete(infoNo);
    }

    /* 탄소 중립글 조회수 증가 */
    @Override
    public int updateCarbonInfoView(int infoNo) {

        return carbonInfoMapper.updateCarbonInfoView(infoNo);
    }


}

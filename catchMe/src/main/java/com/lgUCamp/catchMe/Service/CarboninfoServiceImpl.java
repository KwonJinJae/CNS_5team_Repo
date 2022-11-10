package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import com.lgUCamp.catchMe.DTO.CarbonInfo;

import com.lgUCamp.catchMe.DTO.CarbonInfoFile;
import com.lgUCamp.catchMe.Mapper.CarbonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CarboninfoServiceImpl implements CarbonInfoService{

    @Autowired
    CarbonInfoMapper carbonInfoMapper;

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    public ArrayList<CarbonInfo> carbonInfoList(SelectCriteria selectCriteria){

            return carbonInfoMapper.carbonInfoList(selectCriteria);
    }

    /* 총 게시물 수 조회 */
    @Override
    public int carbonInfoCount(Map<String, String> searchMap) {
        return carbonInfoMapper.carbonInfoCount(searchMap);
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
    public int carbonInfoModify(CarbonInfo carbonInfo) {

        return carbonInfoMapper.carbonInfoModify(carbonInfo);
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

    /* 파일 저장을 위해 해당 게시글 번호를 불러온다. */
    @Override
    public int carbonInfoNoSelect() {
        return carbonInfoMapper.carbonInfoNoSelect();
    }

    /* 파일 저장 */
    @Override
    public void carbonInfoFileInsert(CarbonInfoFile carbonInfoFile) {

        carbonInfoMapper.carbonInfoFileInsert(carbonInfoFile);
    }

    /* 정보글 삭제 시 파일도 삭제 */
    @Override
    public int carbonInfoFileDelete(int infoNo) {
        return carbonInfoMapper.carbonInfoFileDelete(infoNo);
    }


}

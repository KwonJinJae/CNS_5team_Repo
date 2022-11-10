package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.CarbonInfo;
import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import com.lgUCamp.catchMe.DTO.CarbonInfoFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface CarbonInfoMapper {

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    ArrayList<CarbonInfo> carbonInfoList();
    ArrayList<CarbonInfo> carbonInfoList(SelectCriteria selectCriteria);

    /* 총 게시물 수 검색 */
    int carbonInfoCount(Map<String, String> searchMap);

    /* 탄소중립 상세 게시글 조회 */
    AdminCarbonFile carbonInfoDetail(int infoNo);

    /* 탄소중립 신규 게시글 등록 */
    void carbonInfoInsert(CarbonInfo carbonInfo);

    /* 탄소중립 수정을 위한 조회 */
    AdminCarbonFile carbonInfoModifySelect(int infoNo);

    /* 탄소 중립 게시글 수정 */
    int carbonInfoModify(CarbonInfo carbonInfo);

    /* 탄소 중립 게시글 삭제 */
    int carbonInfoDelete(int infoNo);

    /* 탄소중립글 조회수 처리 */
    @Update("UPDATE TBL_CARBON_INFO SET INFO_VIEW = INFO_VIEW + 1 WHERE INFO_NO = #{infoNo}")
    int updateCarbonInfoView(int infoNo);

    /* 파일 저장 */
    void carbonInfoFileInsert(CarbonInfoFile carbonInfoFile);

    /* 파일 저장을 위해 해당 게시글 번호를 불러온다. */
    int carbonInfoNoSelect ();

    /* 정보글 삭제 시 파일도 삭제 */
    int carbonInfoFileDelete(int infoNo);
}

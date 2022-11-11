package com.lgUCamp.catchMe.Service;


import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.CarbonInfoDTO;
import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import com.lgUCamp.catchMe.DTO.CarbonInfoFileDTO;

import java.util.ArrayList;
import java.util.Map;

public interface CarbonInfoService {

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    ArrayList<CarbonInfoDTO> carbonInfoList(SelectCriteria selectCriteria);

    /* 총 게시물 수 검색 */
    int carbonInfoCount(Map<String, String> searchMap);

    /* 탄소중립 상세 게시글 조회 */
    AdminCarbonFile carbonInfoDetail(int infoNo);

    /* 탄소중립 신규 게시글 등록 */
    void carbonInfoInsert(CarbonInfoDTO carbonInfo);

    /* 탄소중립 수정을 위한 조회 */
    AdminCarbonFile carbonInfoModifySelect(int infoNo);

    /* 탄소 중립 게시글 수정 */
    int carbonInfoModify(CarbonInfoDTO carbonInfo);

    /* 탄소 중립 게시글 삭제 */
    int carbonInfoDelete(int infoNo);

    /* 탄소 중립글 조회수 증가 */
    int updateCarbonInfoView(int infoNo);

    /* 파일 저장을 위해 해당 게시글 번호를 불러온다. */
    int carbonInfoNoSelect ();

    /* 파일 저장 */
    void carbonInfoFileInsert(CarbonInfoFileDTO carbonInfoFile);

    /* 정보글 삭제 시 파일도 삭제 */
    int carbonInfoFileDelete(int infoNo);
}

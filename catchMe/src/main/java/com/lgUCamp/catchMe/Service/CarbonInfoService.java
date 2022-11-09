package com.lgUCamp.catchMe.Service;


import com.lgUCamp.catchMe.DTO.CarbonInfo;
import com.lgUCamp.catchMe.DTO.AdminCarbonFile;

import java.util.ArrayList;

public interface CarbonInfoService {

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    ArrayList<CarbonInfo> carbonInfoList();

    /* 탄소중립 상세 게시글 조회 */
    AdminCarbonFile carbonInfoDetail(int infoNo);

    /* 탄소중립 신규 게시글 등록 */
    void carbonInfoInsert(CarbonInfo carbonInfo);

    /* 탄소중립 수정을 위한 조회 */
    AdminCarbonFile carbonInfoModifySelect(int infoNo);

    /* 탄소 중립 게시글 수정 */
    void carbonInfoModify(CarbonInfo carbonInfo);

    /* 탄소 중립 게시글 삭제 */
    int carbonInfoDelete(int infoNo);

    /* 탄소 중립글 조회수 증가 */
    int updateCarbonInfoView(int infoNo);
}
